package com.vironit.bouquetService.dao;

import com.vironit.bouquetService.dao.interfaces.OrderDAO;
import com.vironit.bouquetService.model.Order;
import com.vironit.bouquetService.config.HibernateSessionFactoryUtil;
import com.vironit.bouquetService.model.User;
import com.vironit.bouquetService.model.enums.OrderStatus;
import com.vironit.bouquetService.model.enums.Role;
import com.vironit.bouquetService.temp.ConnectionPool;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
//import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {


    @Override
    public void add(Order order) throws SQLException {
        //        connection = dbConnector.getConnection();

        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "INSERT INTO ORD(ADDRESS, PHONE_NUMBER, STATUS, FK_USERS) VALUES (?, ?, ?::ORDER_STATUS, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, order.getAddress());
            preparedStatement.setString(2, order.getPhoneNumber());
            preparedStatement.setString(3, order.getStatus().toString());
            preparedStatement.setLong(4, order.getUser().getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.close();
        }
    }

    @Override
    public List<Order> getAll() throws SQLException {
        //        connection = dbConnector.getConnection();
        Connection connection = ConnectionPool.getInstance().getConnection();

        List<Order> orderList = new ArrayList<>();

        String sql = "SELECT ORD_ID, ADDRESS, PHONE_NUMBER, STATUS, FK_USERS " +
                "USERS_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ROLE FROM ORD " +
                "INNER JOIN USERS " +
                "ON USERS_ID = FK_USERS ORDER BY ORD_ID";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Order order = new Order();
                User user = new User();
                user.setId(resultSet.getLong("USERS_ID"));
                user.setFirstName(resultSet.getString("FIRST_NAME"));
                user.setLastName(resultSet.getString("LAST_NAME"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setUserRole(Role.valueOf(resultSet.getString("ROLE")));
                order.setUser(user);

                order.setId(resultSet.getLong("ORD_ID"));
                order.setAddress(resultSet.getString("ADDRESS"));
                order.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
                order.setStatus(OrderStatus.valueOf(resultSet.getString("STATUS")));
                order.setUser(order.getUser());
                orderList.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.close();
        }

        return orderList;
    }

    @Override
    public Order getById(long id) throws SQLException{
        //        connection = dbConnector.getConnection();
        Connection connection = ConnectionPool.getInstance().getConnection();

        Order order = new Order();
        User user = new User();

        String sql = "SELECT ORD_ID, ADDRESS, PHONE_NUMBER, STATUS, USERS_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ROLE " +
                "FROM ORD INNER JOIN USERS " +
                "ON USERS_ID = FK_USERS " +
                "WHERE ORD_ID = ? ORDER BY ORD_ID";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet;

            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getLong("USERS_ID"));
                user.setFirstName(resultSet.getString("FIRST_NAME"));
                user.setLastName(resultSet.getString("LAST_NAME"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setUserRole(Role.valueOf(resultSet.getString("ROLE")));

                order.setId(resultSet.getLong("ORD_ID"));
                order.setAddress(resultSet.getString("ADDRESS"));
                order.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
                order.setStatus(OrderStatus.valueOf(resultSet.getString("STATUS")));
                order.setUser(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.close();
        }

        return order;
    }

    @Override
    public void update(Order order) throws SQLException {
        //        connection = dbConnector.getConnection();
        Connection connection = ConnectionPool.getInstance().getConnection();

        String sql = "UPDATE ORD SET ADDRESS = ?, PHONE_NUMBER = ?, STATUS = ?::ORDER_STATUS, FK_USERS = ? WHERE ORD_ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, order.getAddress());
            preparedStatement.setString(2, order.getPhoneNumber());
            preparedStatement.setString(3, order.getStatus().toString());
            preparedStatement.setLong(4, order.getUser().getId());
            preparedStatement.setLong(5, order.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.close();
        }
    }

    @Override
    public void remove(long id) throws SQLException {
        //        connection = dbConnector.getConnection();
        Connection connection = ConnectionPool.getInstance().getConnection();


        String sql = "DELETE FROM ORD WHERE ORD_ID = ?";
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
