package com.vironit.bouquetService.dao.interfaces;

import com.vironit.bouquetService.model.Bouquet;

import java.sql.SQLException;
import java.util.List;

public interface BouquetDAO {

    void add(Bouquet bouquet) throws SQLException;

    List<Bouquet> getAll() throws SQLException;

    Bouquet getById(long id) throws SQLException;

    void update(Bouquet bouquet) throws SQLException;

    void remove(long id) throws SQLException;

}
