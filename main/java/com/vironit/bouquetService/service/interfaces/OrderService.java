package com.vironit.bouquetService.service.interfaces;

import com.vironit.bouquetService.model.Bouquet;
import com.vironit.bouquetService.model.FlowerInBouquet;
import com.vironit.bouquetService.model.Order;
import com.vironit.bouquetService.model.User;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {

    void add(Order order) throws SQLException;

    List<Order> getAll() throws SQLException;

    Order getById(long id) throws SQLException;

    List<Order> getByUser(User user) throws SQLException;

    void update(Order order) throws SQLException;

    void remove(long id) throws SQLException;

    boolean createOrder(Order order, Bouquet bouquet, List<FlowerInBouquet> flowerInBouquets);
}
