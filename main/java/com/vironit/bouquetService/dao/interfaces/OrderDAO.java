package com.vironit.bouquetService.dao.interfaces;

import com.vironit.bouquetService.model.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {

    void add(Order order) throws SQLException;

    List<Order> getAll() throws SQLException;

    Order getById(long id) throws SQLException;

    void update(Order order) throws SQLException;

    void remove(long id) throws SQLException;

}
