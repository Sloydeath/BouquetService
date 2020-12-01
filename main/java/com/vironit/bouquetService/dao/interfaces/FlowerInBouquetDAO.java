package com.vironit.bouquetService.dao.interfaces;

import com.vironit.bouquetService.model.FlowerInBouquet;

import java.sql.SQLException;
import java.util.List;

public interface FlowerInBouquetDAO {

    void add(FlowerInBouquet flowerInBouquet) throws SQLException;

    List<FlowerInBouquet> getAll() throws SQLException;

    FlowerInBouquet getById(long id) throws SQLException;

    void update(FlowerInBouquet flowerInBouquet) throws SQLException;

    void remove(long id) throws SQLException;

}
