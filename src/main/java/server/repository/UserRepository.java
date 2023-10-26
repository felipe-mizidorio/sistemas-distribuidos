
package server.repository;

import jakarta.persistence.Persistence;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import server.entity.User;
import server.exceptions.BadRequestException;
import server.exceptions.ResourceNotFoundException;
import server.exceptions.ServerResponseException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserRepository implements Repository{

    private final SessionFactory sessionFactory;

    public UserRepository() {
        this.sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("server.entity");
    }

    public Optional<User> login(String email) {
        try(Session session = sessionFactory.openSession()) {
            return session.bySimpleNaturalId(User.class).loadOptional(email);
        }
    }

    public void create(User newInstance) throws ServerResponseException {
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
                throw new BadRequestException("Usuário com email " + newInstance.getEmail() + " já existe.");
            }
        }
    }

    public User update(Long id, User instance) throws ServerResponseException {
        try (Session session = sessionFactory.openSession()) {

            User user = session.byId(User.class)
                    .loadOptional(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário com id: " + id + " não existe."));


            var userWithEmail = session.bySimpleNaturalId(User.class)
                    .loadOptional(instance.getEmail());

            if (userWithEmail.isPresent() && !Objects.equals(userWithEmail.get().getRegistro(), instance.getRegistro())) {
                throw new BadRequestException("Usuário com email " + userWithEmail.get().getEmail() + " já existe.");
            }


            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                user.update(instance);
                session.merge(user);
                tx.commit();
            } catch (RollbackException ignored) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new BadRequestException("Usuário com email " + user.getEmail() + " já existe.");
            }
            return user;
        }
    }

    public Optional<User> find(Long id) {
        try(Session session = sessionFactory.openSession()) {
            var user = session.find(User.class, id);
            return Optional.ofNullable(user);
        }
    }

    public List<User> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User", User.class).getResultList();
        }
    }

    public void deleteById(Long id) {
        sessionFactory.inTransaction(session -> {
            User user = session.find(User.class, id);
            session.createMutationQuery("delete from User where id = :id")
                    .setParameter("id", user.getRegistro())
                    .executeUpdate();
        });
    }

    public boolean tryDelete(Long id) {
        try (var session = sessionFactory.openSession()) {
            long numberOfAdmins = session.createSelectionQuery("select count(*) from User user where user.isAdmin = :admin", Long.class)
                    .setParameter("admin", true)
                    .uniqueResult();
            if (numberOfAdmins < 2) {
                return false;
            }

            deleteById(id);
            return true;
        }
    }
}