package com.vironit.bouquetService.service;

import com.vironit.bouquetService.dao.BouquetDAOImpl;
import com.vironit.bouquetService.model.Bouquet;
import com.vironit.bouquetService.service.interfaces.BouquetService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

public class BouquetServiceImpl implements BouquetService {

/*    private final BouquetDAOImpl bouquetDAO;

    @Autowired
    public BouquetServiceImpl(BouquetDAOImpl bouquetDAO) {
        this.bouquetDAO = bouquetDAO;
    }*/

    private BouquetDAOImpl bouquetDAO = new BouquetDAOImpl();

    @Override
    public void add(Bouquet bouquet) throws SQLException {
        bouquetDAO.add(bouquet);
    }

    @Override
    public List<Bouquet> getAll() throws SQLException {
        return bouquetDAO.getAll();
    }

    @Override
    public Bouquet getById(long id) throws SQLException {
        return bouquetDAO.getById(id);
    }

    @Override
    public void update(Bouquet bouquet) throws SQLException {
        bouquetDAO.update(bouquet);
    }

    @Override
    public void remove(long id) throws SQLException {
        bouquetDAO.remove(id);
    }
}
