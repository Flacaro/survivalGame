package org.example.persistence;

import jakarta.persistence.EntityManager;
import org.example.model.Game;
import org.example.model.Map;

public class MapDao {
    private EntityManager em= EntityManagerSingleton.getEntityManager();

    public void saveMap(Map map) {
        try {
            em.getTransaction().begin();
            em.persist(map); // Salva nel database
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
