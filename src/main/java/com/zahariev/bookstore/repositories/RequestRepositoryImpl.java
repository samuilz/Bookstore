package com.zahariev.bookstore.repositories;

import com.zahariev.bookstore.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RequestRepositoryImpl implements RequestRepository {
    private final ConnectionHelper connectionHelper;

    @Autowired
    public RequestRepositoryImpl(ConnectionHelper connectionHelper) {
        this.connectionHelper = connectionHelper;
    }

    @Override
    public Request save(Request request) {
        String query = "INSERT INTO requests(product_id, user_id, request_type) VALUES(?, ?, ?)";

        try (
                Connection connection = connectionHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, request.getProduct().getProductId());
            statement.setInt(2, request.getUser().getUserId());
            statement.setString(3, request.getRequestType().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return request;
    }

    @Override
    public List<Request> getAll() {
        String query = "SELECT * FROM requests \n" +
                "INNER JOIN products ON requests.product_id = products.product_id\n" +
                "INNER JOIN users ON requests.user_id = users.user_id;";
        List<Request> requests = new ArrayList<>();

        try (
                Connection connection = connectionHelper.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            requests = readData(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return requests;
    }

    private List<Request> readData(ResultSet requestData) throws SQLException {
        List<Request> requests = new ArrayList<>();
        while (requestData.next()) {
            Integer id = requestData.getInt("id");
            Integer productId = requestData.getInt("product_id");
            String productName = requestData.getString("name");
            Integer productStock = requestData.getInt("stock");
            Double productPrice = requestData.getDouble("price");

            String productType = requestData.getString("product_type");

            Request request = new Request();
            request.setId(id);
            if (productType.equals("Book")) {
                String authorsName = requestData.getString("authors_name");
                Book book = new Book(productId, productName, productStock, productPrice, authorsName);
                request.setProduct(book);
            } else if (productType.equals("BoardGame")) {
                Integer minPlayers = requestData.getInt("minimum_number_of_players");
                Integer maxPlayers = requestData.getInt("maximum_number_of_players");
                BoardGame boardGame = new BoardGame(productId, productName, productStock, productPrice, minPlayers, maxPlayers);
                request.setProduct(boardGame);
            } else {
                Integer pieces = requestData.getInt("pieces");
                Puzzle puzzle = new Puzzle(productId, productName, productStock, productPrice, pieces);
                request.setProduct(puzzle);
            }

            requests.add(request);
        }

        return requests;
    }

    @Override
    public void delete(Request request) {
        String query = "DELETE FROM requests WHERE id = ?";

        try (
                Connection connection = connectionHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, request.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Request getById(Integer id) {
        String query = "SELECT * FROM requests \n" +
                "INNER JOIN products ON requests.product_id = products.product_id\n" +
                "INNER JOIN users ON requests.user_id = users.user_id\n" +
                "WHERE id = ?;\n";
        Request request = null;

        try (
                Connection connection = connectionHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, id);
            try (
                    ResultSet resultSet = statement.executeQuery();
            ) {
                request = readData(resultSet).get(0);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return request;
    }
}
