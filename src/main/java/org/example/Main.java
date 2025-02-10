package org.example;

import Persistence.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.openSession();

    }
}