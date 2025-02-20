package org.example.persistence;

import jakarta.persistence.EntityManager;
import org.example.model.Game;

public class GameDao {

    public void saveGame(Game game, EntityManager em) {
        try {
            em.getTransaction().begin();
            em.persist(game); // Salva nel database
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

}

