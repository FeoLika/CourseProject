package ru.feolika.Tasks.w2d4.entityDAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.feolika.Tasks.w2d4.models.Student;
import ru.feolika.Tasks.w2d4.models.Subject;

import java.util.List;

public class SubjectDAO implements IDAO<Subject> {
    private SessionFactory sessionFactory;

    public SubjectDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Subject entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        }
    }

    @Override
    public void update(Subject entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Subject entity = session.get(Subject.class, id);
            session.delete(entity);
            transaction.commit();
        }
    }

    @Override
    public List<Subject> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Subject> subjects = session.createQuery("from Subject", Subject.class).list();
            transaction.commit();
            return subjects;
        }
    }

    @Override
    public Subject findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Subject subject = session.get(Subject.class, id);
            transaction.commit();
            return subject;
        }
    }
}
