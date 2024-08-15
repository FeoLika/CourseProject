package ru.feolika.Tasks.w2d3.Repository.StudentsSubjects;

import ru.feolika.Tasks.w2d3.Repository.DBUtils;
import ru.feolika.Tasks.w2d3.Repository.Students.Student;
import ru.feolika.Tasks.w2d3.Repository.Subjects.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * JDBC репозиторий для работы с таблицей students_subjects
 */
public class StudentsSubjectsJDBC {
    private final String SQL_GET_STUDENTS_OF_SUBJECT = """
            SELECT *
            FROM subjects
            LEFT JOIN students_subjects
                ON subjects.subject_id = students_subjects.subject_id
            LEFT JOIN students
                ON students.student_id = students_subjects.student_id
            WHERE subjects.subject_id = ?
            """;
    private final String SQL_GET_SUBJECTS_OF_STUDENT = """
            SELECT *
            FROM students
            LEFT JOIN students_subjects
                ON students.student_id = students_subjects.student_id
            LEFT JOIN subjects
                ON subjects.subject_id = students_subjects.subject_id
            WHERE students.student_id = ?
            """;
    private final String SQL_GET_COUNT_OF_SAME_PAIR = "SELECT COUNT(*) FROM students_subjects WHERE student_id = ? AND subject_id = ?";
    private final String SQL_GET_STUDENT = "SELECT * FROM students_subjects WHERE student_id = ?";
    private final String SQL_GET_SUBJECT = "SELECT * FROM students_subjects WHERE subject_id = ?";
    private final String SQL_INSERT = "INSERT INTO students_subjects (student_id, subject_id) VALUES (?, ?)";
    private final String SQL_DELETE = "DELETE FROM students_subjects WHERE student_id = ? AND subject_id = ?";

    public void addSubjectToStudent(int studentID, int subjectID) throws SQLException {

        try (Connection connection = DBUtils.openConnection();
             PreparedStatement hasPairInTable = connection.prepareStatement(SQL_GET_COUNT_OF_SAME_PAIR);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)) {
            hasPairInTable.setInt(1, studentID);
            hasPairInTable.setInt(2, subjectID);

            ResultSet result = hasPairInTable.executeQuery();

            if (result.next() && result.getInt(1) != 0) {
                return;
            }
            preparedStatement.setInt(1, studentID);
            preparedStatement.setInt(2, subjectID);
            preparedStatement.executeUpdate();
        }
    }

    private boolean isSubjectHasStudent(int subjectID) throws SQLException {
        try (Connection connection = DBUtils.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_SUBJECT)) {
            preparedStatement.setInt(1, subjectID);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next() && result.getInt("student_id") != 0)
                return true;
            else
                throw new SQLException("Этот предмет никто не изучает");
        }
    }

    private boolean isStudentHasSubject(int studentID) throws SQLException {
        try (Connection connection = DBUtils.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_STUDENT)) {
            preparedStatement.setInt(1, studentID);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next() && result.getInt("subject_id") != 0)
                return true;
            else
                throw new SQLException("Этот студент ничего не изучает");
        }
    }

    public void removeSubjectFromStudent(int studentID, int subjectID) throws SQLException {
        if (isStudentHasSubject(studentID) && isSubjectHasStudent(subjectID)) {
            try (Connection connection = DBUtils.openConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
                preparedStatement.setInt(1, studentID);
                preparedStatement.setInt(2, subjectID);
                preparedStatement.executeUpdate();
            }
        }
    }

    public void addStudentToSubject(int studentID, int subjectID) throws SQLException {
        addSubjectToStudent(studentID, subjectID);
    }

    public void removeStudentFromSubject(int studentID, int subjectID) throws SQLException {
        removeSubjectFromStudent(studentID, subjectID);
    }

    public ArrayList<Subject> getStudentSubjects(int studentID) throws SQLException {
        ArrayList<Subject> subjects = new ArrayList<>();

        if (isStudentHasSubject(studentID)) {
            try (Connection connection = DBUtils.openConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_SUBJECTS_OF_STUDENT)) {
                preparedStatement.setInt(1, studentID);
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    Subject subject = new Subject();
                    subject.setID(result.getInt("subject_id"));
                    subject.setTitle(result.getString("title"));
                    subject.setDescription(result.getString("description"));
                    subjects.add(subject);
                }
                return subjects;
            }
        }
        return subjects;
    }

    public ArrayList<Student> getSubjectStudents(int subjectID) throws SQLException {
        ArrayList<Student> students = new ArrayList<>();

        if (isSubjectHasStudent(subjectID)) {
            try (Connection connection = DBUtils.openConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_STUDENTS_OF_SUBJECT)) {
                preparedStatement.setInt(1, subjectID);
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    Student student = new Student();
                    student.setID(result.getInt("student_id"));
                    student.setName(result.getString("name"));
                    student.setSurname(result.getString("surname"));
                    student.setBirthday(result.getDate("birthday"));
                    students.add(student);
                }
                return students;
            }
        }
        return students;
    }
}
