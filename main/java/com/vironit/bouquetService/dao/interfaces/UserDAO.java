package com.vironit.bouquetService.dao.interfaces;

import com.vironit.bouquetService.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    void add(User user) throws SQLException;

    List getAll() throws SQLException;

    User getById(long id) throws SQLException;

    void update(User user) throws SQLException;

    void remove(long id) throws SQLException;

}
