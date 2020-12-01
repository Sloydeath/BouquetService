package com.vironit.bouquetService.dao;

import com.vironit.bouquetService.dao.interfaces.UserDAO;
import com.vironit.bouquetService.config.HibernateSessionFactoryUtil;
import com.vironit.bouquetService.model.User;
import com.vironit.bouquetService.model.enums.Role;
import com.vironit.bouquetService.temp.ConnectionPool;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
//import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAOImpl implements UserDAO{

    @Override
    public void add(User user) throws SQLException{

        String sql = "INSERT INTO USERS(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ROLE) VALUES (?, ?, ?, ?, ?::ROLE)";
        Connection connection = ConnectionPool.getInstance().getConnection();
//        connection = dbConnector.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getUserRole().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.close();
        }
    }

    @Override
    public List<User> getAll() throws SQLException{
        //        connection = dbConnector.getConnection();
        Connection connection = ConnectionPool.getInstance().getConnection();

        List<User> userList = new ArrayList<>();
        String sql = "SELECT USERS_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ROLE FROM USERS";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("USERS_ID"));
                user.setFirstName(resultSet.getString("FIRST_NAME"));
                user.setLastName(resultSet.getString("LAST_NAME"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setUserRole(Role.valueOf(resultSet.getString("ROLE")));
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.close();
        }

        return userList;
    }

    @Override
    public User getById(long id) throws SQLException{
        //        connection = dbConnector.getConnection();
        Connection connection = ConnectionPool.getInstance().getConnection();

        User user = new User();

        String sql = "SELECT USERS_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ROLE FROM USERS WHERE USERS_ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user.setId(resultSet.getLong("USERS_ID"));
                user.setFirstName(resultSet.getString("FIRST_NAME"));
                user.setLastName(resultSet.getString("LAST_NAME"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setUserRole(Role.valueOf(resultSet.getString("ROLE")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.close();
        }

        return user;
    }

    @Override
    public void update(User user) throws SQLException{
        //        connection = dbConnector.getConnection();
        Connection connection = ConnectionPool.getInstance().getConnection();

        String sql = "UPDATE USERS SET FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ?, PASSWORD = ?, ROLE = ?::ROLE WHERE USERS_ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getUserRole().toString());
            preparedStatement.setLong(6, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.close();
        }
    }

    @Override
    public void remove(long id) throws SQLException{
        //        connection = dbConnector.getConnection();
        Connection connection = ConnectionPool.getInstance().getConnection();

        String sql = "DELETE FROM USERS WHERE USERS_ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.close();
        }
    }
}
