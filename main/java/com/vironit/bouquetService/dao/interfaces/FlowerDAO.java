package com.vironit.bouquetService.dao.interfaces;

import com.vironit.bouquetService.model.Flower;

import java.sql.SQLException;
import java.util.List;

public interface FlowerDAO {

    void add(Flower flower) throws SQLException;

    List<Flower> getAll() throws SQLException;

    Flower getById(long id) throws SQLException;

    void update(Flower flower) throws SQLException;

    void remove(long id) throws SQLException;

}
