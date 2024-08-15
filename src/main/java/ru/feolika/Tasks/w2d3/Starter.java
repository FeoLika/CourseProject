package ru.feolika.Tasks.w2d3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.feolika.Tasks.w2d3.Repository.DBUtils;
import ru.feolika.Tasks.w2d3.Repository.Students.Student;
import ru.feolika.Tasks.w2d3.Repository.Students.StudentJDBC;
import ru.feolika.Tasks.w2d3.Repository.StudentsSubjects.StudentsSubjectsJDBC;
import ru.feolika.Tasks.w2d3.Repository.Subjects.Subject;
import ru.feolika.Tasks.w2d3.Repository.Subjects.SubjectJDBC;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Starter {
    private static final Logger LOGGER = LogManager.getLogger(ru.feolika.Tasks.w1d7.Starter.class);
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static void main(String[] args) {
        try {
            DBUtils.createTables();

            StudentJDBC studentJDBC = new StudentJDBC();
            SubjectJDBC subjectJDBC = new SubjectJDBC();
            StudentsSubjectsJDBC studentsSubjectsJDBC = new StudentsSubjectsJDBC();

            Student student1 = new Student("Аня", "Иванова", new Date(dateFormat.parse("01.01.2000").getTime()));
            Student student2 = new Student("Вася", "Кузнецов", new Date(dateFormat.parse("10.09.1980").getTime()));
            Student student3 = new Student("Женя", "Петров", new Date(dateFormat.parse("14.10.1999").getTime()));

            Subject subject1 = new Subject("Мат. анализ", "Математика для умных");
            Subject subject2 = new Subject("История России", "История для патриотов");
            Subject subject3 = new Subject("Основы алгоритмики", "Простые алгоритмы");

//            studentJDBC.addEntity(student1);
//            studentJDBC.addEntity(student2);
//            studentJDBC.addEntity(student3);
//
//            subjectJDBC.addEntity(subject1);
//            subjectJDBC.addEntity(subject2);
//            subjectJDBC.addEntity(subject3);

            studentJDBC.getAllEntities().forEach(LOGGER::info);
            subjectJDBC.getAllEntities().forEach(LOGGER::info);

            LOGGER.info("Студент по ID = 1: {}", studentJDBC.getEntity(1));
            LOGGER.info("Студент по ID = 4: {}", studentJDBC.getEntity(4));

            LOGGER.info("Предмет по ID = 1: {}", subjectJDBC.getEntity(1));
            LOGGER.info("Предмет по ID = 4: {}", subjectJDBC.getEntity(4));

            student1.setSurname("Кузнецова");
            studentJDBC.updateEntity(1, student1);
            LOGGER.info("Студент по ID = 1 сменил фамилию: {}", studentJDBC.getEntity(1));

            subject1.setTitle("Математический анализ");
            subjectJDBC.updateEntity(1, subject1);
            LOGGER.info("Предмет по ID = 1 с полной расшифровкой : {}", subjectJDBC.getEntity(1));

            studentsSubjectsJDBC.addSubjectToStudent(1, 1);
            LOGGER.info("Cтудент " + student1.getName() + " " + student1.getSurname() + " записался на предмет " + subject1.getTitle());

            studentsSubjectsJDBC.addSubjectToStudent(1, 2);
            LOGGER.info("Cтудент " + student1.getName() + " " + student1.getSurname() + " записался на предмет " + subject2.getTitle());

            studentsSubjectsJDBC.addSubjectToStudent(2, 1);
            LOGGER.info("Cтудент " + student2.getName() + " " + student2.getSurname() + " записался на предмет " + subject1.getTitle());

            LOGGER.info("Все предметы студента " + student1.getName() + " " + student1.getSurname() + ":");
            studentsSubjectsJDBC.getStudentSubjects(1).forEach(LOGGER::info);
            LOGGER.info("Все студенты предмета " + subject1.getTitle() + ":");
            studentsSubjectsJDBC.getSubjectStudents(1).forEach(LOGGER::info);

            studentsSubjectsJDBC.removeStudentFromSubject(1, 1);
            LOGGER.info("Cтудент " + student1.getName() + " " + student1.getSurname() + " удалился с предмета " + subject1.getTitle());

            LOGGER.info("Все предметы студента " + student1.getName() + " " + student1.getSurname() + ":");
            studentsSubjectsJDBC.getStudentSubjects(1).forEach(LOGGER::info);
            LOGGER.info("Все студенты предмета " + subject1.getTitle() + ":");
            studentsSubjectsJDBC.getSubjectStudents(1).forEach(LOGGER::info);

        } catch (SQLException | ParseException e) {
            LOGGER.error(e);
        }
    }
}
