package hexlet.code;

import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/library_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "12122323";
    private Connection connection;

    public void connect() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addBook(String title, String author, Date publishedDate, String isbn) {
        try {
            Statement st = connection.prepareStatement("SELECT * FROM books");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getAllBooks() {
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM books");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
