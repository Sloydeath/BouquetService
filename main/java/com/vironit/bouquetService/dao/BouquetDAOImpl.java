package com.vironit.bouquetService.dao;

import com.vironit.bouquetService.config.HibernateSessionFactoryUtil;
import com.vironit.bouquetService.dao.interfaces.BouquetDAO;
import com.vironit.bouquetService.model.Bouquet;
import com.vironit.bouquetService.model.Order;
import com.vironit.bouquetService.model.enums.BouquetType;
import com.vironit.bouquetService.model.enums.OrderStatus;
import com.vironit.bouquetService.temp.ConnectionPool;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BouquetDAOImpl implements BouquetDAO {

    private static final Logger log = Logger.getLogger(BouquetDAOImpl.class);

    @Override
    public void add(Bouquet bouquet) throws SQLException{
        //        connection = dbConnector.getConnection();

        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement;

        String sql = "INSERT INTO BOUQUET(TYPE, FK_ORD) VALUES (?::BOUQUET_TYPE, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, bouquet.getType().toString());
            preparedStatement.setLong(2, bouquet.getOrder().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Bouquet> getAll() throws SQLException{
        //        connection = dbConnector.getConnection();
        Connection connection = ConnectionPool.getInstance().getConnection();

        List<Bouquet> bouquetList = new ArrayList<>();

        String sql = "SELECT BOUQUET_ID, TYPE, FK_ORD, ORD_ID, ADDRESS, PHONE_NUMBER, STATUS" +
                "FROM BOUQUET " +
                "INNER JOIN ORD " +
                "ON ORD_ID = FK_ORD ORDER BY BOUQUET_ID";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Bouquet bouquet = new Bouquet();
                Order order = new Order();

                order.setId(resultSet.getLong("ORD_ID"));
                order.setAddress(resultSet.getString("ADDRESS"));
                order.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
                order.setStatus(OrderStatus.valueOf(resultSet.getString("STATUS")));
                bouquet.setOrder(order);


                bouquet.setId(resultSet.getLong("BOUQUET_ID"));
                bouquet.setType(BouquetType.valueOf(resultSet.getString("TYPE")));
                bouquet.setOrder(bouquet.getOrder());
                bouquetList.add(bouquet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.close();
        }

        return bouquetList;
    }

    @Override
    public Bouquet getById(long id) throws SQLException{
        //        connection = dbConnector.getConnection();
        Connection connection = ConnectionPool.getInstance().getConnection();

        Order order = new Order();
        Bouquet bouquet = new Bouquet();

        String sql = "SELECT BOUQUET_ID, TYPE, FK_ORD, ORD_ID, ADDRESS, PHONE_NUMBER, STATUS " +
                "FROM BOUQUET " +
                "INNER JOIN ORD " +
                "ON ORD_ID = FK_ORD WHERE BOUQUET_ID = ? ORDER BY BOUQUET_ID";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet;
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order.setId(resultSet.getLong("ORD_ID"));
                order.setAddress(resultSet.getString("ADDRESS"));
                order.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
                order.setStatus(OrderStatus.valueOf(resultSet.getString("STATUS")));

                bouquet.setId(resultSet.getLong("BOUQUET_ID"));
                bouquet.setType(BouquetType.valueOf(resultSet.getString("TYPE")));
                bouquet.setOrder(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.close();
        }

        return bouquet;
    }

    @Override
    public void update(Bouquet bouquet) throws SQLException{
        //        connection = dbConnector.getConnection();
        Connection connection = ConnectionPool.getInstance().getConnection();

        String sql = "UPDATE BOUQUET SET TYPE = ?::BOUQUET_TYPE, FK_ORD = ? WHERE BOUQUET_ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, bouquet.getType().toString());
            preparedStatement.setLong(2, bouquet.getOrder().getId());
            preparedStatement.setLong(3, bouquet.getId());

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

        String sql = "DELETE FROM BOUQUET WHERE BOUQUET_ID = ?";
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
