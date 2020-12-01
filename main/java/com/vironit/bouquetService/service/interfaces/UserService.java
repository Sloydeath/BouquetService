package com.vironit.bouquetService.service.interfaces;

import com.vironit.bouquetService.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    void add(User user) throws SQLException;

    List getAll() throws SQLException;

    User getById(long id) throws SQLException;

    User getByEmailPassword(String email, String password) throws SQLException;

    User getByEmail(String email) throws SQLException;

    void update(User user) throws SQLException;

    void remove(long id) throws SQLException;

    boolean isExistByEmailPassword(String email, String password) throws SQLException;

    boolean isExistByEmail(String email) throws SQLException;

}
