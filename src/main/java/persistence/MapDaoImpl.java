package persistence;

import jakarta.persistence.EntityManager;
import model.Map;

public class MapDaoImpl implements MapDao {

    public void saveMap(Map map, EntityManager em) {
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
