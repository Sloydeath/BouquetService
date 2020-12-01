package com.vironit.bouquetService.dao;

import com.vironit.bouquetService.dao.interfaces.FlowerInBouquetDAO;
import com.vironit.bouquetService.config.HibernateSessionFactoryUtil;
import com.vironit.bouquetService.model.FlowerInBouquet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
//import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

public class FlowerInBouquetDAOImpl implements FlowerInBouquetDAO {

    @Override
    public void add(FlowerInBouquet flowerInBouquet) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(flowerInBouquet);
        transaction.commit();
        session.close();
    }

    @Override
    public List<FlowerInBouquet> getAll() throws SQLException {
        String hql = "FROM FLOWER_IN_BOUQUET F_B ORDER BY F_B.id";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(hql);
        List<FlowerInBouquet> flowerInBouquets = query.list();
        transaction.commit();
        session.close();
        return flowerInBouquets;
    }

    @Override
    public FlowerInBouquet getById(long id) throws SQLException {
        FlowerInBouquet flowerInBouquet ;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        flowerInBouquet = session.get(FlowerInBouquet.class, id);

        transaction.commit();
        session.close();
        return flowerInBouquet;
    }

    @Override
    public void update(FlowerInBouquet flowerInBouquet) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(flowerInBouquet);

        transaction.commit();
        session.close();
    }

    @Override
    public void remove(long id) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        FlowerInBouquet flower = (FlowerInBouquet) session.get(FlowerInBouquet.class, id);
        session.delete(flower);

        transaction.commit();
        session.close();
    }
}
