package com.vironit.bouquetService.service;

import com.vironit.bouquetService.config.HibernateSessionFactoryUtil;
import com.vironit.bouquetService.dao.BouquetDAOImpl;
import com.vironit.bouquetService.dao.FlowerDAOImpl;
import com.vironit.bouquetService.dao.FlowerInBouquetDAOImpl;
import com.vironit.bouquetService.dao.OrderDAOImpl;
import com.vironit.bouquetService.model.*;
import com.vironit.bouquetService.service.interfaces.OrderService;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static final Logger log = Logger.getLogger(OrderServiceImpl.class);

    private OrderDAOImpl orderDAO = new OrderDAOImpl();
    private BouquetDAOImpl bouquetDAO = new BouquetDAOImpl();
    private FlowerDAOImpl flowerDAO = new FlowerDAOImpl();
    private FlowerInBouquetDAOImpl flowerInBouquetDAO = new FlowerInBouquetDAOImpl();

    @Override
    public void add(Order order) throws SQLException {
        orderDAO.add(order);
    }

    @Override
    public List<Order> getAll() throws SQLException {
        return orderDAO.getAll();
    }

    @Override
    public Order getById(long id) throws SQLException {
        return orderDAO.getById(id);
    }

    @Override
    public List<Order> getByUser(User user) throws SQLException {
        String hql = "FROM Order O WHERE O.user = :user ORDER BY O.id";
        List<Order> orders;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery(hql);
        query.setParameter("user", user);

        orders = query.list();

        transaction.commit();
        session.close();
        return orders;
    }

    @Override
    public void update(Order order) throws SQLException {
        orderDAO.update(order);
    }

    @Override
    public void remove(long id) throws SQLException {
        orderDAO.remove(id);
    }

    @Override
    public boolean createOrder(Order order, Bouquet bouquet, List<FlowerInBouquet> flowerInBouquets) {
        Transaction transaction = null;
        Flower flower = null;

        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            orderDAO.add(order);
            bouquetDAO.add(bouquet);

            for (FlowerInBouquet fB : flowerInBouquets) {
                flowerInBouquetDAO.add(fB);
                flower = flowerDAO.getById(fB.getFlower().getId());
                flower.setQuality(flower.getQuality() - fB.getQuality());
                flowerDAO.update(flower);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
                log.error(e);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e);
        }
        return true;
    }
}
