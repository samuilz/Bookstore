package com.zahariev.bookstore.repositories;

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
        String query = "SELECT * FROM puzzles;";
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
    public List<Puzzle> getAllByNumberOfPiecesAndPartOfTitle(String pieces, String partOfTitle) {
        String query;

        switch (pieces) {
            case "less":
                query = "SELECT * FROM puzzles WHERE pieces < 1000 AND LOCATE(?, name) > 0;";
                break;
            case "onethousand":
                query = "SELECT * FROM puzzles WHERE pieces = 1000 AND LOCATE(?, name) > 0;";
                break;
            case "twothousands":
                query = "SELECT * FROM puzzles WHERE pieces = 2000 AND LOCATE(?, name) > 0;";
                break;
            case "threethousands":
                query = "SELECT * FROM puzzles WHERE pieces < 3000 AND LOCATE(?, name) > 0;";
                break;
            case "more":
                query = "SELECT * FROM puzzles WHERE pieces > 3000 AND LOCATE(?, name) > 0;";
                break;
            default:
                query = "SELECT * FROM puzzles WHERE LOCATE(?, name) > 0;";
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
    public Puzzle purchasePuzzle(Puzzle puzzle) {
        return null;
    }

    private List<Puzzle> readData(ResultSet puzzlesData) throws SQLException {
        List<Puzzle> puzzles = new ArrayList<>();
        while (puzzlesData.next()) {
            Integer id = puzzlesData.getInt("id");
            String name = puzzlesData.getString("name");
            Integer stock = puzzlesData.getInt("stock");
            Double price = puzzlesData.getDouble("prize");
            Integer pieces = puzzlesData.getInt("pieces");
            Puzzle puzzle = new Puzzle(id, name, stock, price, pieces);

            puzzles.add(puzzle);
        }

        return puzzles;
    }
}
