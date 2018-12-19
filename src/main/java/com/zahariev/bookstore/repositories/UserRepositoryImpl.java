package com.zahariev.bookstore.repositories;

import com.zahariev.bookstore.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final ConnectionHelper connectionHelper;

    @Autowired
    public UserRepositoryImpl(ConnectionHelper connectionHelper) {
        this.connectionHelper = connectionHelper;
    }

    @Override
    public User save(User user) {
        String query = "INSERT INTO users(address, first_name, last_name, phone_number)" +
                " VALUES(?, ?, ?, ?)";
        String selectQuery = "SELECT * FROM users WHERE address = ? AND first_name = ?" +
                "AND last_name = ? AND phone_number = ?";

        User selectedUser = null;

        try (
                Connection connection = connectionHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
        ) {
            statement.setString(1, user.getAddress());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getPhoneNumber());
            statement.executeUpdate();

            selectStatement.setString(1, user.getAddress());
            selectStatement.setString(2, user.getFirstName());
            selectStatement.setString(3, user.getLastName());
            selectStatement.setString(4, user.getPhoneNumber());

            try (
                    ResultSet resultSet = selectStatement.executeQuery();
            ) {
                selectedUser = readData(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return selectedUser;
    }

    private User readData(ResultSet userData) throws SQLException {
        userData.next();
        Integer id = userData.getInt("user_id");
        String address = userData.getString("address");
        String firstName = userData.getString("first_name");
        String lastName = userData.getString("last_name");
        String phoneNumber = userData.getString("phone_number");

        return new User(id, address, firstName, lastName, phoneNumber);
    }
}
