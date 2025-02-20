package org.example.persistence;

import jakarta.persistence.EntityManager;
import org.example.model.Map;

public class MapDaoImpl implements MapDao {

private final EntityManager em= EntityManagerSingleton.getEntityManager();

public void saveMap(Map map) {
    try {
        em.getTransaction().begin();
        em.persist(map); // Salva nel database
        em.getTransaction().commit();
    } catch (Exception e) {
        e.printStackTrace();
        em.getTransaction().rollback();
    }

}
}
