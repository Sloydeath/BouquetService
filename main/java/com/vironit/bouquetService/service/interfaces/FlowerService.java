package com.vironit.bouquetService.service.interfaces;

import com.vironit.bouquetService.model.Flower;

import java.sql.SQLException;
import java.util.List;

public interface FlowerService {

    void add(Flower flower) throws SQLException;

    List<Flower> getAll() throws SQLException;

    Flower getById(long id) throws SQLException;

    List getByName(String name) throws SQLException;

    Flower getByNameColorLength(String name, String color, short length) throws SQLException;

    void update(Flower flower) throws SQLException;

    void remove(long id) throws SQLException;

    boolean isExistByNameColorLength(String name, String color, short length) throws SQLException;

}
