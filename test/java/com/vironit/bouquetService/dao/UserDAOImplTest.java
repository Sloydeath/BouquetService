package com.vironit.bouquetService.dao;

import com.vironit.bouquetService.service.UserServiceImpl;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserDAOImplTest {

    @Test
    public void isExistByEmail() throws SQLException {
        UserServiceImpl userService = new UserServiceImpl();
        boolean actual = userService.isExistByEmail("sloydeath@mail.ru");
        boolean expected = true;
        assertEquals(expected, actual);
    }
}