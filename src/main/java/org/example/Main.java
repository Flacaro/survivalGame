package org.example;

import Persistence.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        System.out.println("Inizializzazione di Hibernate...");
        HibernateUtil.getSessionFactory();
        System.out.println("Hibernate inizializzato con successo!");

        // Puoi decidere di terminare qui, oppure fare altre operazioni
        HibernateUtil.shutdown();

    }
}