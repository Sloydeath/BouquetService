package com.vironit.bouquetService.service;

import com.vironit.bouquetService.dao.FlowerDAOImpl;
import com.vironit.bouquetService.model.Flower;
import com.vironit.bouquetService.model.enums.FlowerColor;
import com.vironit.bouquetService.model.enums.FlowerName;
import com.vironit.bouquetService.service.interfaces.FlowerService;
import com.vironit.bouquetService.config.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

public class FlowerServiceImpl implements FlowerService {

    private FlowerDAOImpl flowerDAO = new FlowerDAOImpl();

/*    private final FlowerDAOImpl flowerDAO;

    @Autowired
    public FlowerServiceImpl(FlowerDAOImpl flowerDAO) {
        this.flowerDAO = flowerDAO;
    }*/

    @Override
    public void add(Flower flower) throws SQLException {
        flowerDAO.add(flower);
    }

    @Override
    public List<Flower> getAll() throws SQLException {
        return flowerDAO.getAll();
    }

    @Override
    public Flower getById(long id) throws SQLException {
        return flowerDAO.getById(id);
    }

    @Override
    public List getByName(String name) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Flower F WHERE F.name = :name ORDER BY F.id";
        Query query = session.createQuery(hql);
        query.setParameter("name", FlowerName.valueOf(name));
        List<Flower> flowers = query.list();
        transaction.commit();
        session.close();
        return flowers;
    }

    @Override
    public Flower getByNameColorLength(String name, String color, short length) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Flower F WHERE F.name = :name AND F.color = :color AND F.length = :length ORDER BY F.id";
        Query query = session.createQuery(hql);
        query.setParameter("name", FlowerName.valueOf(name));
        query.setParameter("color", FlowerColor.valueOf(color));
        query.setParameter("length", length);
        Flower flower = (Flower) query.uniqueResult();
        transaction.commit();
        session.close();
        return flower;
    }

    @Override
    public void update(Flower flower) throws SQLException {
        flowerDAO.update(flower);
    }

    @Override
    public void remove(long id) throws SQLException {
        flowerDAO.remove(id);
    }

    @Override
    public boolean isExistByNameColorLength(String name, String color, short length) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        boolean result = false;
        Flower flower;
        String hql = "FROM Flower F WHERE F.name = :name AND F.color = :color AND F.length = :length";
        Query query = session.createQuery(hql);
        query.setParameter("name", FlowerName.valueOf(name));
        query.setParameter("color", FlowerColor.valueOf(color));
        query.setParameter("length", length);
        flower = (Flower) query.uniqueResult();

        if(flower != null)
            result = true;

        transaction.commit();
        session.close();
        return result;
    }
}
