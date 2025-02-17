package org.example.persistence;

import jakarta.persistence.EntityManager;
import org.example.model.Game;

public class GameDao {
    private EntityManager em= EntityManagerSingleton.getEntityManager();

    public void saveGame(Game game) {
        try {
            em.getTransaction().begin();
            em.persist(game); // Salva nel database
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void close() {
        em.close();
    }
}

