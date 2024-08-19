package ru.feolika.Tasks.w2d4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import ru.feolika.Tasks.w2d4.entityDAO.StudentDAO;
import ru.feolika.Tasks.w2d4.entityDAO.SubjectDAO;
import ru.feolika.Tasks.w2d4.models.Student;
import ru.feolika.Tasks.w2d4.models.Subject;
import ru.feolika.Tasks.w2d4.session.SessionFactoryUtil;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Starter {
    private static final Logger LOGGER = LogManager.getLogger(ru.feolika.Tasks.w1d7.Starter.class);
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();) {

            //init students and subjects
            StudentDAO studentDAO = new StudentDAO(sessionFactory);
            Student student1 = new Student("Аня", "Иванова", new Date(dateFormat.parse("01.01.2000").getTime()), new ArrayList<>());
            Student student2 = new Student("Вася", "Кузнецов", new Date(dateFormat.parse("10.09.1980").getTime()), new ArrayList<>());
            Student student3 = new Student("Женя", "Петров", new Date(dateFormat.parse("14.10.1999").getTime()), new ArrayList<>());

            SubjectDAO subjectDAO = new SubjectDAO(sessionFactory);
            Subject subject1 = new Subject("Мат. анализ", "Математика для умных", new ArrayList<>());
            Subject subject2 = new Subject("История России", "История для патриотов", new ArrayList<>());
            Subject subject3 = new Subject("Основы алгоритмики", "Простые алгоритмы", new ArrayList<>());
            subjectDAO.save(subject1);
            subjectDAO.save(subject2);
            subjectDAO.save(subject3);

            // Add subjects to students
            student1.setSubjects(Arrays.asList(subject1, subject3));
            student2.setSubjects(List.of(subject1));
            student3.setSubjects(Arrays.asList(subject2, subject3));

            studentDAO.save(student1);
            studentDAO.save(student2);
            studentDAO.save(student3);

            // Get students and subjects
            List<Student> students = studentDAO.findAll();
            List<Subject> subjects = subjectDAO.findAll();
            LOGGER.info("Студенты: {}", students);
            LOGGER.info("Предметы: {}", subjects);

            // Update student surname
            students.getFirst().setSurname("Кузнецова");
            studentDAO.update(students.getFirst());
            Student updatedStudent = studentDAO.findById(1L);
            LOGGER.info(updatedStudent.toString());

            // Remove subject from student
            updatedStudent.getSubjects().forEach(LOGGER::info);
            Subject subject = subjectDAO.findById(3L);
            updatedStudent.getSubjects().remove(subject);
            studentDAO.update(updatedStudent);
            updatedStudent.getSubjects().forEach(LOGGER::info);

        } catch (ParseException e) {
            LOGGER.info(e.getMessage());
        }
    }
}
