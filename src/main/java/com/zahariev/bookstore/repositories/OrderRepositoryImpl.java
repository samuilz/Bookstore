package com.zahariev.bookstore.repositories;

import com.zahariev.bookstore.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private final ConnectionHelper connectionHelper;

    @Autowired
    public OrderRepositoryImpl(ConnectionHelper connectionHelper) {
        this.connectionHelper = connectionHelper;
    }

    @Override
    public Order save(Order order) {
        String query = "INSERT INTO orders(product_id, user_id) VALUES(?, ?)";

        try (
                Connection connection = connectionHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, order.getProduct().getProductId());
            statement.setInt(2, order.getUser().getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return order;
    }
}
