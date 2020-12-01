package com.vironit.bouquetService;

import com.vironit.bouquetService.config.HibernateSessionFactoryUtil;
import com.vironit.bouquetService.dao.UserDAOImpl;
import com.vironit.bouquetService.dao.interfaces.UserDAO;
import com.vironit.bouquetService.model.Flower;
import com.vironit.bouquetService.model.User;
import com.vironit.bouquetService.model.enums.Role;
import com.vironit.bouquetService.service.FlowerServiceImpl;
import com.vironit.bouquetService.service.interfaces.FlowerService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        FlowerService flowerService = new FlowerServiceImpl();
        List<Flower> flowers = flowerService.getAll();
        for(Flower flower: flowers){
            System.out.println(flower);
        }
    }
}
