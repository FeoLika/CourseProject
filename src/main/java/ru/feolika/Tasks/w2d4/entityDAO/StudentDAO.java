package ru.feolika.Tasks.w2d4.entityDAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.feolika.Tasks.w2d4.models.Student;

import java.util.List;

public class StudentDAO implements IDAO<Student> {
    private SessionFactory sessionFactory;

    public StudentDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Student entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        }
    }

    @Override
    public void update(Student entity) {
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
            Student entity = session.get(Student.class, id);
            session.delete(entity);
            transaction.commit();
        }
    }

    @Override
    public List<Student> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Student> students = session.createQuery("from Student", Student.class).list();
            transaction.commit();
            return students;
        }
    }

    @Override
    public Student findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);
            transaction.commit();
            return student;
        }
    }
}
