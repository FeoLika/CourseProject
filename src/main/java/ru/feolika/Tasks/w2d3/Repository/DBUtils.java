package ru.feolika.Tasks.w2d3.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Класс для обращения к бд
 */
public final class DBUtils {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/MyDB";
    private static final String DB_LOGIN = "postgres";
    private static final String DB_PASSWORD = "admin";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Подключение к бд
     *
     * @return Connection
     * @throws SQLException
     */
    public static Connection openConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
    }

    /**
     * Создание таблиц в бд
     *
     * @throws SQLException
     */
    public static void createTables() throws SQLException {
        try (Connection dbConnection = openConnection(); Statement statement = dbConnection.createStatement()) {
            String studentsTable = """
                    CREATE TABLE IF NOT EXISTS students (
                        student_id SERIAL PRIMARY KEY,
                        name VARCHAR(100),
                        surname VARCHAR(100),
                        birthday DATE);
                    """;
            String subjectsTable = """
                    CREATE TABLE IF NOT EXISTS subjects (
                        subject_id SERIAL PRIMARY KEY,
                        title VARCHAR(100),
                        description VARCHAR(100));
                    """;
            String studentsSubjectsTable = """
                    CREATE TABLE IF NOT EXISTS students_subjects (
                        student_id INT,
                        subject_id INT,
                        PRIMARY KEY (student_id, subject_id),
                        FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
                        FOREIGN KEY (subject_id) REFERENCES subjects(subject_id) ON DELETE CASCADE
                    );
                    """;
            statement.execute(studentsTable);
            statement.execute(subjectsTable);
            statement.execute(studentsSubjectsTable);
        }
    }
}
