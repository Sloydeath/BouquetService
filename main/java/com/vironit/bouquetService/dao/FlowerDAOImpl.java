package com.vironit.bouquetService.dao;

import com.vironit.bouquetService.dao.interfaces.FlowerDAO;
import com.vironit.bouquetService.config.HibernateSessionFactoryUtil;
//import org.apache.log4j.Logger;
import com.vironit.bouquetService.model.Bouquet;
import com.vironit.bouquetService.model.Flower;
import com.vironit.bouquetService.model.enums.BouquetType;
import com.vironit.bouquetService.model.enums.FlowerColor;
import com.vironit.bouquetService.model.enums.FlowerName;
import com.vironit.bouquetService.temp.ConnectionPool;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
//import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlowerDAOImpl implements FlowerDAO {

//    private Logger log = Logger.getLogger(FlowerDAOImpl.class);

    @Override
    public void add(Flower flower) throws SQLException {
        //        connection = dbConnector.getConnection();
        Connection connection = ConnectionPool.getInstance().getConnection();

        PreparedStatement preparedStatement;

        String sql = "INSERT INTO FLOWER(NAME, LENGTH, COLOR, QUALITY, PRICE, FK_BOUQUET) VALUES (?::FLOWER_NAME, ?, ?::FLOWER_COLOR, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, flower.getName().toString());
            preparedStatement.setShort(2, flower.getLength());
            preparedStatement.setString(3, flower.getColor().toString());
            preparedStatement.setDouble(4, flower.getPrice());
            preparedStatement.setInt(5, flower.getQuality());
            if(flower.getBouquet() != null)
                preparedStatement.setLong(6, flower.getBouquet().getId());
            else preparedStatement.setObject(6, null);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.close();
        }
    }

    @Override
    public List<Flower> getAll() throws SQLException {
        //        connection = dbConnector.getConnection();
        Connection connection = ConnectionPool.getInstance().getConnection();

        List<Flower> flowerList = new ArrayList<>();

        String sql = "SELECT FLOWER_ID ,NAME, LENGTH, COLOR, QUALITY, PRICE, FK_BOUQUET, BOUQUET_ID, TYPE" +
                "FROM FLOWER " +
                "FULL JOIN BOUQUET " +
                "ON BOUQUET_ID = FK_BOUQUET ORDER BY FLOWER_ID";

        try {
            Statement statement = connection.createStatement();
            try {
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    Flower flower = new Flower();
                    Bouquet bouquet = new Bouquet();

                    bouquet.setId(resultSet.getLong("BOUQUET_ID"));
                    if (bouquet.getId() != 0) {
                        bouquet.setType(BouquetType.valueOf(resultSet.getString("TYPE")));
                        flower.setBouquet(bouquet);
                    } else flower.setBouquet(null);
                    flower.setId(resultSet.getLong("FLOWER_ID"));
                    flower.setName(FlowerName.valueOf(resultSet.getString("NAME")));
                    flower.setLength(resultSet.getShort("LENGTH"));
                    flower.setColor(FlowerColor.valueOf(resultSet.getString("COLOR")));
                    flower.setQuality(resultSet.getInt("QUALITY"));
                    flower.setPrice(resultSet.getFloat("PRICE"));
                    flowerList.add(flower);
                }

            } finally {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.close();
        }

        return flowerList;
    }

    @Override
    public Flower getById(long id) throws SQLException {
        //        connection = dbConnector.getConnection();
        Connection connection = ConnectionPool.getInstance().getConnection();

        Flower flower = new Flower();
        Bouquet bouquet = new Bouquet();

        String sql = "SELECT FLOWER_ID ,NAME, LENGTH, COLOR, PRICE, FK_BOUQUET, BOUQUET_ID, TYPE " +
                "FROM FLOWER FULL JOIN BOUQUET " +
                "ON BOUQUET_ID = FK_BOUQUET " +
                "WHERE FLOWER_ID = ? ORDER BY FLOWER_ID";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet;

            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bouquet.setId(resultSet.getLong("BOUQUET_ID"));
                bouquet.setType(BouquetType.valueOf(resultSet.getString("TYPE")));

                flower.setId(resultSet.getLong("ORD_ID"));
                flower.setName(FlowerName.valueOf(resultSet.getString("NAME")));
                flower.setLength(resultSet.getShort("LENGTH"));
                flower.setColor(FlowerColor.valueOf(resultSet.getString("COLOR")));
                flower.setPrice(resultSet.getFloat("PRICE"));
                flower.setBouquet(bouquet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.close();
        }

        return flower;
    }

    @Override
    public void update(Flower flower) throws SQLException {
        //        connection = dbConnector.getConnection();
        Connection connection = ConnectionPool.getInstance().getConnection();

        String sql = "UPDATE FLOWER SET NAME = ?, LENGTH = ?, COLOR = ?, PRICE = ?, FK_BOUQUET = ? WHERE FLOWER_ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, flower.getName().toString());
            preparedStatement.setShort(2, flower.getLength());
            preparedStatement.setString(3, flower.getColor().toString());
            preparedStatement.setDouble(4, flower.getPrice());
            preparedStatement.setLong(5, flower.getBouquet().getId());
            preparedStatement.setLong(6, flower.getId());

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

        String sql = "DELETE FROM FLOWER WHERE FLOWER_ID = ?";
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
