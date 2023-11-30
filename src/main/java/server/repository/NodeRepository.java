package server.repository;

import jakarta.persistence.Persistence;
import jakarta.persistence.RollbackException;
import jakarta.persistence.UniqueConstraint;
import json.validation.ConstraintViolated;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import server.entity.Node;
import server.exceptions.BadRequestException;
import server.exceptions.ResourceNotFoundException;
import server.exceptions.ServerResponseException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class NodeRepository {
    private final SessionFactory sessionFactory;

    public NodeRepository() {
        this.sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("server.entity");
    }

    public void create(Node newInstance) throws ServerResponseException {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.persist(newInstance);
                transaction.commit();
            } catch(RollbackException e) {
                if(transaction != null) {
                    transaction.rollback();
                }
                throw new BadRequestException("Ponto com nome " + newInstance.getNome() + " já existe.");
            }
        }
    }

    public Node update(Long idNode, Node instance) throws ServerResponseException {
        try (Session session = sessionFactory.openSession()) {

            Node node = session.byId(Node.class)
                    .loadOptional(idNode)
                    .orElseThrow(() -> new ResourceNotFoundException("Ponto com id " + idNode + " não existe."));


            var nodeWithName = session.bySimpleNaturalId(Node.class)
                    .loadOptional(instance.getNome());

            if (nodeWithName.isPresent() && !Objects.equals(nodeWithName.get().getId(), instance.getId())) {
                throw new BadRequestException("Ponto com id " + nodeWithName.get().getNome() + " já existe.");
            }


            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                node.update(instance);
                session.merge(node);
                tx.commit();
            } catch (RollbackException ignored) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new BadRequestException("Node com nome " + node.getNome() + " já existe.");
            }
            return node;
        }
    }

    public Optional<Node> find(Long id) {
        try(Session session = sessionFactory.openSession()) {
            var node = session.find(Node.class, id);
            return Optional.ofNullable(node);
        }
    }

    public List<Node> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Node", Node.class).getResultList();
        }
    }

    public void delete(Long id) {
        sessionFactory.inTransaction(session -> {
            Node node = session.find(Node.class, id);
            session.createMutationQuery("delete from Node where id = :id")
                    .setParameter("id", node.getId())
                    .executeUpdate();
        });
    }
}
