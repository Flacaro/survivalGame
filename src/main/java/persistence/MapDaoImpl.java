package persistence;

import jakarta.persistence.EntityManager;
import model.entity.Map;

public class MapDaoImpl implements MapDao {

    public void saveMap(Map map, EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            em.persist(map);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

    }


}
