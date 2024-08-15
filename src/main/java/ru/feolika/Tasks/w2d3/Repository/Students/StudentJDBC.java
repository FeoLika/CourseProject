package ru.feolika.Tasks.w2d3.Repository.Students;

import ru.feolika.Tasks.w2d3.Repository.DBUtils;
import ru.feolika.Tasks.w2d3.Repository.IDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * JDBC репозиторий для работы с таблицей students
 */
public class StudentJDBC implements IDAO<Student> {
    private final String SQL_GET_ALL = "SELECT * FROM students";
    private final String SQL_GET_BY_ID = "SELECT * FROM students WHERE student_id = ?";
    private final String SQL_INSERT = "INSERT INTO students (name, surname, birthday) VALUES (?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE students SET name = ?, surname = ?, birthday = ? WHERE student_id = ?";
    private final String SQL_DELETE = "DELETE FROM students WHERE student_id = ?";

    @Override
    public void addEntity(Student entity) throws SQLException {
        try (Connection connection = DBUtils.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);) {

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getSurname());
            preparedStatement.setDate(3, entity.getBirthday());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setID(generatedKeys.getInt(1));
            }
        }
    }

    @Override
    public void removeEntity(Student entity) throws SQLException {
        if (getEntity(entity.getID()) == null) {
            try (Connection connection = DBUtils.openConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);) {

                preparedStatement.setInt(1, entity.getID());
                preparedStatement.executeUpdate();
            }
        } else {
            throw new SQLException("Такого студента не существует");
        }
    }

    @Override
    public void updateEntity(int id, Student entity) throws SQLException {
        if (getEntity(id) != null) {
            try (Connection connection = DBUtils.openConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);) {

                preparedStatement.setString(1, entity.getName());
                preparedStatement.setString(2, entity.getSurname());
                preparedStatement.setDate(3, entity.getBirthday());
                preparedStatement.setInt(4, id);

                preparedStatement.executeUpdate();
            }
        } else {
            throw new SQLException("Такого студента не существует");
        }
    }

    @Override
    public Student getEntity(int ID) throws SQLException {
        try (Connection connection = DBUtils.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_ID);) {

            preparedStatement.setInt(1, ID);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {

                Student student = new Student();
                student.setID(result.getInt("student_id"));
                student.setName(result.getString("name"));
                student.setSurname(result.getString("surname"));
                student.setBirthday(result.getDate("birthday"));

                return student;
            } else return null;
        }
    }

    @Override
    public ArrayList<Student> getAllEntities() throws SQLException {
        ArrayList<Student> array = new ArrayList<>();

        try (Connection connection = DBUtils.openConnection();
             ResultSet result = connection.prepareStatement(SQL_GET_ALL).executeQuery();) {
            while (result.next()) {
                Student student = new Student();
                student.setID(result.getInt("student_id"));
                student.setName(result.getString("name"));
                student.setSurname(result.getString("surname"));
                student.setBirthday(result.getDate("birthday"));
                array.add(student);
            }
            return array;
        }
    }
}
