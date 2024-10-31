package hexlet.code;


import hexlet.code.models.Book;
import hexlet.code.models.Reader;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DatabaseManager db = new DatabaseManager();

    public static void main(String[] args) {
        db.connect();
        manager();
    }
    private static void manager() {
        System.out.println("Simple console db manager active!");
        System.out.println("1 - books db");
        System.out.println("2 - readers db");
        System.out.println("3 - exit");
        String userAnswer = askAndGetUserAnswer("Print number what you need.");

        switch (userAnswer) {
            case "1":
                booksDB();
                break;
            case "2":
                readersDB();
                break;
            case "3":
                stop();
                break;
            default:
                System.out.println("Wrong command!");
                stop();
                break;
        }
    }

    private static void booksDB() {
        System.out.println("Books management console.");
        System.out.println("1 - add book");
        System.out.println("2 - get all books");
        System.out.println("3 - find book by title");
        System.out.println("4 - delete book by id");
        System.out.println("5 - exit to main menu");
        System.out.println("6 - exit");
        String userAnswer = askAndGetUserAnswer("Print number what you need.");

        switch (userAnswer) {
            case "1":
                String title = askAndGetUserAnswer("Write book title: ");
                String author = askAndGetUserAnswer("Write book author: ");
                Date publishedDate = new Date();
                String isbn = askAndGetUserAnswer("Write book isbn: ");
                try {
                    db.addBook(title, author, publishedDate, isbn);
                } catch (Exception e) {
                    System.out.println("DB error or books already exist!");
                }
                System.out.println("Print any to continue...");
                scanner.next();
                booksDB();
                break;
            case "2":
                ArrayList<Book> books = db.getAllBooks();
                books.forEach(System.out::println);
                System.out.println("Print any to continue...");
                scanner.next();
                booksDB();
                break;
            case "3":
                String searchTitle = askAndGetUserAnswer("Write book title: ");
                Book book = db.findBookByTitle(searchTitle);
                System.out.println(book);
                System.out.println("Print any to continue...");
                scanner.next();
                booksDB();
                break;
            case "4":
                int searchId;
                try {
                    System.out.println("Print reader id: ");
                    searchId = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Received not a number!");
                    break;
                }
                db.deleteBook(searchId);
                System.out.println("Print any to continue...");
                scanner.next();
                booksDB();
                break;
            case "5":
                manager();
                break;
            case "6":
                stop();
                break;
            default:
                System.out.println("Wrong command!");
                stop();
                break;
        }
    }

    private static void readersDB() {
        System.out.println("Welcome to readers management console!");
        System.out.println("1 - add reader");
        System.out.println("2 - get all readers");
        System.out.println("3 - find reader by email");
        System.out.println("4 - delete reader by id");
        System.out.println("5 - exit to main menu");
        System.out.println("6 - exit");
        String userAnswer = askAndGetUserAnswer("Print number what you need.");

        switch (userAnswer) {
            case "1":
                String name = askAndGetUserAnswer("Write reader name: ");
                String email = askAndGetUserAnswer("Write reader email: ");
                try {
                    db.addReader(name, email);
                } catch (Exception e) {
                    System.out.println("DB error or user with this email already exist!");
                }
                System.out.println("Print any to continue...");
                scanner.next();
                readersDB();
                break;
            case "2":
                ArrayList<Reader> readers = db.getAllReaders();
                readers.forEach(System.out::println);
                System.out.println("Print any to continue...");
                scanner.next();
                readersDB();
                break;
            case "3":
                String searchEmail = askAndGetUserAnswer("Write reader name: ");
                Reader reader = db.findReaderByEmail(searchEmail);
                System.out.println(reader);
                System.out.println("Print any to continue...");
                scanner.next();
                readersDB();
                break;
            case "4":
                int searchId;
                try {
                    System.out.println("Print reader id: ");
                    searchId = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Received not a number!");
                    break;
                }
                db.deleteReader(searchId);
                System.out.println("Print any to continue...");
                scanner.next();
                readersDB();
                break;
            case "5":
                manager();
                break;
            case "6":
                stop();
                break;
            default:
                System.out.println("Wrong command!");
                stop();
                break;
        }
    }

    private static void stop() {
        db.closeConnection();
        System.out.println("Manager stopped");
    }

    private static String askAndGetUserAnswer(String question) {
        System.out.println(question);
        return scanner.next();
    }
}
