package server.repository;

import jakarta.persistence.Persistence;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import server.entity.Segment;
import server.exceptions.BadRequestException;
import server.exceptions.ResourceNotFoundException;
import server.exceptions.ServerResponseException;

import java.util.List;
import java.util.Optional;

public class SegmentRepository {
    private final SessionFactory sessionFactory;

    public SegmentRepository() {
        this.sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("server.entity");
    }

    public void create(Segment newInstance) throws ServerResponseException {
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
                throw new BadRequestException("Segmento entre os pontos " +
                        newInstance.getPdi_inicial() + " e " + newInstance.getPdi_final() + " já existe.");
            }
        }
    }

    public Segment update(Long idSegment, Segment instance) throws ServerResponseException {
        try (Session session = sessionFactory.openSession()) {
            Segment segment = session.byId(Segment.class)
                    .loadOptional(idSegment)
                    .orElseThrow(() -> new ResourceNotFoundException("Segmento com id " + idSegment + " não existe."));

            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                segment.update(instance);
                session.merge(segment);
                tx.commit();
            } catch (RollbackException ignored) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new BadRequestException("Segmento entre os pontos " +
                        instance.getPdi_inicial() + " e " + instance.getPdi_final() + " já existe.");
            }
            return segment;
        }
    }

    public Optional<Segment> find(Long pdiInicial, Long pdiFinal) {
        try(Session session = sessionFactory.openSession()) {
            String query = "FROM Segment WHERE pdi_inicial = :pdiInicial AND pdi_final = :pdiFinal";
            return session.createQuery(query, Segment.class)
                    .setParameter("pdiInicial", pdiInicial)
                    .setParameter("pdiFinal", pdiFinal)
                    .uniqueResultOptional();
        }
    }

    public Optional<Segment> find(Long idSegment) {
        try(Session session = sessionFactory.openSession()) {
            String query = "FROM Segment WHERE id = :idSegment";
            return session.createQuery(query, Segment.class)
                    .setParameter("idSegment", idSegment)
                    .uniqueResultOptional();
        }
    }

    public List<Segment> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Segment" , Segment.class).getResultList();
        }
    }

    public void delete(Long id) {
        sessionFactory.inTransaction(session -> {
            Segment segment = session.find(Segment.class, id);
            session.createMutationQuery("delete from Segment where id = :id")
                    .setParameter("id", segment.getId())
                    .executeUpdate();
        });
    }

    public List<Segment> findNeighborsNodeSegments(Long pdi) {
        try(Session session = sessionFactory.openSession()) {
            String query = "FROM Segment WHERE pdi_inicial = :pdi AND acessivel = true";
            return session.createQuery(query, Segment.class)
                    .setParameter("pdi", pdi)
                    .getResultList();
        }
    }
}
