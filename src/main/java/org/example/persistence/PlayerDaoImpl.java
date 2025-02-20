package org.example.persistence;

import jakarta.persistence.EntityManager;
import org.example.model.Player;

public class PlayerDaoImpl implements PlayerDao {

    private final EntityManager em= EntityManagerSingleton.getEntityManager();

    public void savePlayer(Player player) {
        try {
            em.getTransaction().begin();
            em.persist(player); // Salva nel database
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

    }



}
