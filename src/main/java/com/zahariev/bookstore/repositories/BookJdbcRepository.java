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
        String query = "SELECT * FROM products WHERE product_type = 'Book';";
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
    public Book getById(Integer id) {
        String query = "SELECT * FROM products WHERE product_id = ?;";
        Book book = null;

        try (
                Connection connection = connectionHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, id);
            try (
                    ResultSet resultSet = statement.executeQuery();
            ) {
                book = readData(resultSet).get(0);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return book;
    }

    @Override
    public List<Book> getAllByPartOfTitle(String partOfName) {
        String query = "SELECT * FROM products WHERE product_type = 'Book' AND LOCATE(?, `name`) > 0;";
        List<Book> books = new ArrayList<>();

        try (
                Connection connection = connectionHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, partOfName);
            try (
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
        String query = "SELECT * FROM products WHERE product_type = 'Book' AND `authors_name` = ?;";
        List<Book> books = new ArrayList<>();

        try (
                Connection connection = connectionHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, authorsName);
            try (
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
    public Book update(Book book) {
        String query = "UPDATE products SET stock = stock - 1 WHERE product_id = ?;";
        String selectQuery = "SELECT * FROM products WHERE product_id = ?";
        Book selectedBook = null;

        try (
                Connection connection = connectionHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
        ) {
            Integer id = book.getProductId();
            statement.setInt(1, id);
            statement.executeUpdate();

            selectStatement.setInt(1, id);
            ResultSet resultSet = selectStatement.executeQuery();

            selectedBook = readData(resultSet).get(0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return selectedBook;
    }

    private List<Book> readData(ResultSet booksData) throws SQLException {
        List<Book> books = new ArrayList<>();
        while (booksData.next()) {
            Integer id = booksData.getInt("product_id");
            String name = booksData.getString("name");
            Integer stock = booksData.getInt("stock");
            Double price = booksData.getDouble("price");
            String authorsName = booksData.getString("authors_name");
            Book book = new Book(id, name, stock, price, authorsName);

            books.add(book);
        }

        return books;
    }
}
