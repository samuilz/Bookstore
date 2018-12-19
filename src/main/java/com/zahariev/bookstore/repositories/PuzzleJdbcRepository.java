package com.zahariev.bookstore.repositories;

import com.zahariev.bookstore.models.Book;
import com.zahariev.bookstore.models.Puzzle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PuzzleJdbcRepository implements PuzzleRepository {
    private final ConnectionHelper connectionHelper;

    @Autowired
    public PuzzleJdbcRepository(ConnectionHelper connectionHelper) {
        this.connectionHelper = connectionHelper;
    }

    @Override
    public List<Puzzle> getAll() {
        String query = "SELECT * FROM products WHERE product_type = 'Puzzle';";
        List<Puzzle> puzzles = new ArrayList<>();

        try (
                Connection connection = connectionHelper.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            puzzles = readData(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return puzzles;
    }

    @Override
    public Puzzle getById(Integer id) {
        String query = "SELECT * FROM products WHERE product_id = ?;";
        Puzzle puzzle = null;

        try (
                Connection connection = connectionHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, id);
            try(
                    ResultSet resultSet = statement.executeQuery();
            ) {
                puzzle = readData(resultSet).get(0);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return puzzle;
    }

    @Override
    public List<Puzzle> getAllByNumberOfPiecesAndPartOfTitle(String pieces, String partOfTitle) {
        String query;

        switch (pieces) {
            case "less":
                query = "SELECT * FROM products WHERE product_type = 'Puzzle' AND pieces < 1000 AND LOCATE(?, name) > 0;";
                break;
            case "onethousand":
                query = "SELECT * FROM products WHERE product_type = 'Puzzle' AND pieces = 1000 AND LOCATE(?, name) > 0;";
                break;
            case "twothousands":
                query = "SELECT * FROM products WHERE product_type = 'Puzzle' AND pieces = 2000 AND LOCATE(?, name) > 0;";
                break;
            case "threethousands":
                query = "SELECT * FROM products WHERE product_type = 'Puzzle' AND pieces < 3000 AND LOCATE(?, name) > 0;";
                break;
            case "more":
                query = "SELECT * FROM products WHERE product_type = 'Puzzle' AND pieces > 3000 AND LOCATE(?, name) > 0;";
                break;
            default:
                query = "SELECT * FROM products WHERE product_type = 'Puzzle' AND LOCATE(?, name) > 0;";
                break;

        }

        List<Puzzle> puzzles = new ArrayList<>();

        try (
                Connection connection = connectionHelper.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, partOfTitle);
            try(
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                puzzles = readData(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return puzzles;
    }

    @Override
    public Puzzle update(Puzzle puzzle) {
        String query = "UPDATE products SET stock = stock - 1 WHERE product_id = ?;";
        String selectQuery = "SELECT * FROM products WHERE product_id = ?";
        Puzzle selectedPuzzle = null;

        try (
                Connection connection = connectionHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
        ) {
            statement.setInt(1, puzzle.getProductId());
            statement.executeQuery();

            selectStatement.setInt(1, puzzle.getProductId());
            ResultSet resultSet = selectStatement.executeQuery();

            selectedPuzzle = readData(resultSet).get(0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return selectedPuzzle;
    }

    private List<Puzzle> readData(ResultSet puzzlesData) throws SQLException {
        List<Puzzle> puzzles = new ArrayList<>();
        while (puzzlesData.next()) {
            Integer id = puzzlesData.getInt("product_id");
            String name = puzzlesData.getString("name");
            Integer stock = puzzlesData.getInt("stock");
            Double price = puzzlesData.getDouble("price");
            Integer pieces = puzzlesData.getInt("pieces");
            Puzzle puzzle = new Puzzle(id, name, stock, price, pieces);

            puzzles.add(puzzle);
        }

        return puzzles;
    }
}
