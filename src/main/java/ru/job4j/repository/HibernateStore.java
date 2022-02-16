package ru.job4j.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Task;
import ru.job4j.model.User;

import java.util.List;
import java.util.function.Function;


public class HibernateStore {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    private HibernateStore() {

    }

    private static class Holder {
        private static final HibernateStore INST = new HibernateStore();
    }

    public static HibernateStore getInstance() {
        return Holder.INST;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public Task create(Task task) {
        return (Task) this.tx(session -> session.save(task));
    }

    public User create(User user) {
        return (User) this.tx(session -> session.save(user));
    }

    public boolean update(int id, boolean done) {
        return this.tx(session -> {
            var query = session.createQuery("update ru.job4j.model.Task SET done = :done where id = :id");
            query.setParameter("done", done);
            query.setParameter("id", id);
            return query.executeUpdate() > 0;
        });
    }

    public List<Task> findAllTasks() {
        return this.tx(session -> session.createQuery("from ru.job4j.model.Task").list());
    }

    public User findUserByEmail(String email) {
        return this.tx(session -> {
            var query = session.createQuery("from ru.job4j.model.User where email = :email");
            query.setParameter("email", email);
            return (User) query.uniqueResult();
        });
    }
}
