package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transaction;

public class EntityManagerSingleton {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");

    private static EntityManager em;

    private EntityManagerSingleton() {
    }

    public static EntityManager getEntityManager() {
        if (em == null || !em.isOpen()) {
            em = entityManagerFactory.createEntityManager();
        }

        return em;
    }

    public static void checkIfTransactionAlreadyBegan() {
        if(!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }


    public static void closeEntityManager() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

}
