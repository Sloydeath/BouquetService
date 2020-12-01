package com.vironit.bouquetService.config;

import com.vironit.bouquetService.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                System.out.println("All is Okay 1");
                configuration.addAnnotatedClass(Order.class);
                System.out.println("All is Okay 2");
                configuration.addAnnotatedClass(Flower.class);
                System.out.println("All is Okay 3");
                configuration.addAnnotatedClass(Bouquet.class);
                System.out.println("All is Okay 4");
                configuration.addAnnotatedClass(FlowerInBouquet.class);
                System.out.println("All is Okay 5");

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Error " + e);
            }
        }
        return sessionFactory;
    }
}