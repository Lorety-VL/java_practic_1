package hexlet.code;

import hexlet.code.models.Book;
import hexlet.code.models.Reader;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "12122323";
    private Connection connection;

    public void connect() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addBook(String title, String author, java.util.Date publishedDate, String isbn) {

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO  books(title, author, publishedDate, isbn) VALUES (?, ?, ?, ?)");
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setDate(3, new java.sql.Date( publishedDate.getTime()));
            statement.setString(4, isbn);

            statement.executeUpdate();
            statement.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> books = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                Date publishedDate = resultSet.getDate("publishedDate");
                String isbn = resultSet.getString("isbn");
                Book book = new Book(id, title, author, publishedDate, isbn);
                books.add(book);
            }
        statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return books;
    }

    public Book findBookByTitle(String searchTitle) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE title = ?");
            statement.setString(1, searchTitle);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                Date publishedDate = resultSet.getDate("publishedDate");
                String isbn = resultSet.getString("isbn");
                return new Book(id, title, author, publishedDate, isbn);
            }

            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void deleteBook(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM books WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addReader(String name, String email) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO readers (name, email) VALUES (?, ?)");
            statement.setString(1, name);
            statement.setString(2, email);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Reader> getAllReaders() {
        ArrayList<Reader> readers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM readers");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                Reader reader = new Reader(id, name, email);
                readers.add(reader);
            }
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return readers;
    }

    public Reader findReaderByEmail(String email) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM readers WHERE email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                return new Reader(id, name, email);
            }
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void deleteReader(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM readers WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
