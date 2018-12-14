package com.zahariev.bookstore.repositories;

import com.zahariev.bookstore.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookJdbcRepository implements BookRepository {
    private final ConnectionHelper connectionHelper;

    @Autowired
    public BookJdbcRepository(ConnectionHelper connectionHelper) {
        this.connectionHelper = connectionHelper;
    }

    @Override
    public List<Book> getAll() {
        String query = "SELECT * FROM books;";
        List<Book> books = new ArrayList<>();

        try (
                Connection connection = connectionHelper.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            books = readData(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return books;
    }

    @Override
    public List<Book> getAllByPartOfTitle(String partOfName) {
        String query = "SELECT * FROM books WHERE LOCATE(?, `name`) > 0;";
        List<Book> books = new ArrayList<>();

        try (
                Connection connection = connectionHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, partOfName);
            try(
                    ResultSet resultSet = statement.executeQuery();
            ) {
                books = readData(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return books;
    }

    @Override
    public List<Book> getAllByAuthorsName(String authorsName) {
        String query = "SELECT * FROM books WHERE `authors_name` = ?;";
        List<Book> books = new ArrayList<>();

        try (
                Connection connection = connectionHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, authorsName);
            try(
                    ResultSet resultSet = statement.executeQuery();
                    ) {
                books = readData(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return books;
    }

    @Override
    public Book purchaseBook(Book book) {
        return null;
    }

    private List<Book> readData(ResultSet booksData) throws SQLException {
        List<Book> books = new ArrayList<>();
        while (booksData.next()) {
            String name = booksData.getString("name");
            Integer stock = booksData.getInt("stock");
            Double price = booksData.getDouble("prize");
            String authorsName = booksData.getString("authors_name");
            Book book = new Book(name, stock, price, authorsName);

            books.add(book);
        }

        return books;
    }
}
