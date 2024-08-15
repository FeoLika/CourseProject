package ru.feolika.Tasks.w2d3.Repository.Subjects;

import ru.feolika.Tasks.w2d3.Repository.DBUtils;
import ru.feolika.Tasks.w2d3.Repository.IDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * JDBC репозиторий для работы с таблицей subjects
 */
public class SubjectJDBC implements IDAO<Subject> {
    private final String SQL_GET_ALL = "SELECT * FROM subjects";
    private final String SQL_GET_BY_ID = "SELECT * FROM subjects WHERE subject_id = ?";
    private final String SQL_INSERT = "INSERT INTO subjects (title, description) VALUES (?, ?)";
    private final String SQL_UPDATE = "UPDATE subjects SET title = ?, description = ? WHERE subject_id = ?";
    private final String SQL_DELETE = "DELETE FROM subjects WHERE subject_id = ?";

    @Override
    public void addEntity(Subject entity) throws SQLException {
        try (Connection connection = DBUtils.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);) {

            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getDescription());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setID(generatedKeys.getInt(1));
            }
        }
    }

    @Override
    public void removeEntity(Subject entity) throws SQLException {
        if (getEntity(entity.getID()) == null) {
            try (Connection connection = DBUtils.openConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);) {

                preparedStatement.setInt(1, entity.getID());
                preparedStatement.executeUpdate();
            }
        } else {
            throw new SQLException("Такого предмета не существует");
        }
    }

    @Override
    public void updateEntity(int id, Subject entity) throws SQLException {
        if (getEntity(id) != null) {
            try (Connection connection = DBUtils.openConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);) {

                preparedStatement.setString(1, entity.getTitle());
                preparedStatement.setString(2, entity.getDescription());
                preparedStatement.setInt(3, id);

                preparedStatement.executeUpdate();
            }
        } else {
            throw new SQLException("Такого предмета не существует");
        }
    }

    @Override
    public Subject getEntity(int ID) throws SQLException {
        try (Connection connection = DBUtils.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_ID);) {

            preparedStatement.setInt(1, ID);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {

                Subject subject = new Subject();
                subject.setID(result.getInt("subject_id"));
                subject.setTitle(result.getString("title"));
                subject.setDescription(result.getString("description"));

                return subject;
            } else return null;
        }
    }

    @Override
    public ArrayList<Subject> getAllEntities() throws SQLException {
        ArrayList<Subject> array = new ArrayList<>();

        try (Connection connection = DBUtils.openConnection();
             ResultSet result = connection.prepareStatement(SQL_GET_ALL).executeQuery();) {
            while (result.next()) {
                Subject subject = new Subject();
                subject.setID(result.getInt("subject_id"));
                subject.setTitle(result.getString("title"));
                subject.setDescription(result.getString("description"));
                array.add(subject);
            }
            return array;
        }
    }
}
