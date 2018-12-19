package com.zahariev.bookstore.repositories;

import com.zahariev.bookstore.models.BoardGame;
import com.zahariev.bookstore.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BoardGameJdbcRepository implements BoardGameRepository {
    private final ConnectionHelper connectionHelper;

    @Autowired
    public BoardGameJdbcRepository(ConnectionHelper connectionHelper) {
        this.connectionHelper = connectionHelper;
    }

    @Override
    public List<BoardGame> getAll() {
        String query = "SELECT * FROM products WHERE product_type = 'BoardGame';";
        List<BoardGame> boardGames = new ArrayList<>();

        try (
                Connection connection = connectionHelper.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            boardGames = readData(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return boardGames;
    }

    @Override
    public BoardGame getById(Integer id) {
        String query = "SELECT * FROM products WHERE product_type = 'BoardGame' AND product_id = ?;";
        BoardGame boardGame = null;

        try (
                Connection connection = connectionHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, id);
            try(
                    ResultSet resultSet = statement.executeQuery();
            ) {
                boardGame = readData(resultSet).get(0);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return boardGame;
    }

    @Override
    public List<BoardGame> getAllByPartOfName(String partOfName) {
        String query = "SELECT * FROM products WHERE product_type = 'BoardGame' AND LOCATE(?, `name`) > 0;";
        List<BoardGame> boardGames = new ArrayList<>();

        try (
                Connection connection = connectionHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, partOfName);
            try(
                    ResultSet resultSet = statement.executeQuery();
            ) {
                boardGames = readData(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return boardGames;
    }

    @Override
    public List<BoardGame> getAllByNumberOfPlayers(Integer numberOfPlayers) {
        String query = "SELECT * FROM products WHERE product_type = 'BoardGame' " +
                "AND minimum_number_of_players <= ? AND maximum_number_of_players >= ?;";
        List<BoardGame> boardGames = new ArrayList<>();

        try (
                Connection connection = connectionHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, numberOfPlayers);
            statement.setInt(2, numberOfPlayers);
            try(
                    ResultSet resultSet = statement.executeQuery();
            ) {
                boardGames = readData(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return boardGames;
    }

    @Override
    public BoardGame update(BoardGame boardGame) {
        String query = "UPDATE products SET stock = stock - 1 WHERE product_id = ?;";
        String selectQuery = "SELECT * FROM products WHERE product_id = ?";
        BoardGame selectedBoardGame = null;

        try (
                Connection connection = connectionHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
        ) {
            statement.setInt(1, boardGame.getProductId());
            statement.executeUpdate();

            selectStatement.setInt(1, boardGame.getProductId());
            ResultSet resultSet = selectStatement.executeQuery();
            selectedBoardGame = readData(resultSet).get(0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return selectedBoardGame;
    }

    private List<BoardGame> readData(ResultSet boardGameData) throws SQLException {
        List<BoardGame> boardGames = new ArrayList<>();
        while (boardGameData.next()) {
            Integer id = boardGameData.getInt("product_id");
            String name = boardGameData.getString("name");
            Integer stock = boardGameData.getInt("stock");
            Double price = boardGameData.getDouble("price");
            Integer minimumPlayers = boardGameData.getInt("minimum_number_of_players");
            Integer maximumPlayers = boardGameData.getInt("maximum_number_of_players");
            BoardGame boardGame = new BoardGame(id, name, stock, price, minimumPlayers, maximumPlayers);

            boardGames.add(boardGame);
        }

        return boardGames;

    }
}
