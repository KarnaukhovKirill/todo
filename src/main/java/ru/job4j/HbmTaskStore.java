package ru.job4j;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;


public class HbmTaskStore {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    private HbmTaskStore() {

    }

    private static class Holder {
        private static final HbmTaskStore INST = new HbmTaskStore();
    }

    public static HbmTaskStore getInstance() {
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

    public void update(int id, boolean done) {
        Session session = sf.openSession();
        session.beginTransaction();
        var query = session.createQuery("update ru.job4j.Task SET done = :done where id = :id");
        query.setParameter("done", done);
        query.setParameter("id", id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    public List<Task> findAll() {
        List<Task> rsl;
        Session session = sf.openSession();
        session.beginTransaction();
        rsl = session.createQuery("from ru.job4j.Task").list();
        session.getTransaction().commit();
        session.close();
        return rsl;
    }
}
