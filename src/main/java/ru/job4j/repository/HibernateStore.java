package ru.job4j.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Task;
import ru.job4j.model.User;

import java.util.List;


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

    public Task create(Task task) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(task);
        session.getTransaction().commit();
        session.close();
        return task;
    }

    public User create(User user) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    public boolean update(int id, boolean done) {
        Session session = sf.openSession();
        session.beginTransaction();
        var query = session.createQuery("update ru.job4j.model.Task SET done = :done where id = :id");
        query.setParameter("done", done);
        query.setParameter("id", id);
        var rsl = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return rsl > 0;
    }

    public List<Task> findAllTasks() {
        List<Task> rsl;
        Session session = sf.openSession();
        session.beginTransaction();
        rsl = session.createQuery("from ru.job4j.model.Task").list();
        session.getTransaction().commit();
        session.close();
        return rsl;
    }

    public User findUserByEmail(String email) {
        Session session = sf.openSession();
        session.beginTransaction();
        var query = session.createQuery("from ru.job4j.model.User where email = :email");
        query.setParameter("email", email);
        var user = (User) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return user;
    }
}
