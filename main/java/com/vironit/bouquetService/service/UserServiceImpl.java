package com.vironit.bouquetService.service;

import com.vironit.bouquetService.dao.UserDAOImpl;
import com.vironit.bouquetService.model.User;
import com.vironit.bouquetService.service.interfaces.UserService;
import com.vironit.bouquetService.config.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


public class UserServiceImpl implements UserService {

    private UserDAOImpl userDAO = new UserDAOImpl();

/*    private final UserDAOImpl userDAO;

    @Autowired
    public UserServiceImpl(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }*/

    @Override
    public void add(User user) throws SQLException {
        userDAO.add(user);
    }

    @Override
    public List getAll() throws SQLException {
        return userDAO.getAll();
    }

    @Override
    public User getById(long id) throws SQLException {
        return userDAO.getById(id);
    }

    @Override
    public User getByEmailPassword(String email, String password) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User user;
        Query query = session.createQuery("FROM User U WHERE U.email = :email AND U.password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);

        user = (User) query.uniqueResult();

        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public User getByEmail(String email) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User user;
        Query query = session.createQuery("FROM User U WHERE U.email = :email");
        query.setParameter("email", email);

        user = (User) query.uniqueResult();

        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public void update(User user) throws SQLException {
        userDAO.update(user);
    }

    @Override
    public void remove(long id) throws SQLException {
        userDAO.remove(id);
    }

    @Override
    public boolean isExistByEmailPassword(String email, String password) throws SQLException {
        boolean result = false;

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User user = null;
        Query query = session.createQuery("FROM User U WHERE U.email = :email AND U.password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);

        user = (User) query.uniqueResult();

        if(user != null)
            if(user.getEmail() != null && user.getPassword() != null)
                if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                    result = true;
                }

        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public boolean isExistByEmail(String email) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        boolean result = false;
        User user;
        Query query = session.createQuery("FROM User U WHERE U.email = :email");
        query.setParameter("email", email);

        user = (User) query.uniqueResult();

        if(user != null)
            if(user.getEmail() != null)
                if(user.getEmail().equals(email)){
                    result = true;
                }

        transaction.commit();
        session.close();
        return result;
    }
}
