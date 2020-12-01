package com.vironit.bouquetService.service;

import com.vironit.bouquetService.dao.FlowerInBouquetDAOImpl;
import com.vironit.bouquetService.model.FlowerInBouquet;
import com.vironit.bouquetService.service.interfaces.FlowerInBouquetService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

public class FlowerInBouquetServiceImpl implements FlowerInBouquetService {

    private FlowerInBouquetDAOImpl flowerInBouquetDAO = new FlowerInBouquetDAOImpl();


/*    private final FlowerInBouquetDAOImpl flowerInBouquetDAO;

    @Autowired
    public FlowerInBouquetServiceImpl(FlowerInBouquetDAOImpl flowerInBouquetDAO) {
        this.flowerInBouquetDAO = flowerInBouquetDAO;
    }*/

    @Override
    public void add(FlowerInBouquet flowerInBouquet) throws SQLException {
        flowerInBouquetDAO.add(flowerInBouquet);
    }

    @Override
    public List<FlowerInBouquet> getAll() throws SQLException {
        return flowerInBouquetDAO.getAll();
    }

    @Override
    public FlowerInBouquet getById(long id) throws SQLException {
        return flowerInBouquetDAO.getById(id);
    }

    @Override
    public void update(FlowerInBouquet flowerInBouquet) throws SQLException {
        flowerInBouquetDAO.update(flowerInBouquet);
    }

    @Override
    public void remove(long id) throws SQLException {
        flowerInBouquetDAO.remove(id);
    }
}
