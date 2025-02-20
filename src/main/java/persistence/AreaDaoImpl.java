package persistence;

import jakarta.persistence.EntityManager;
import model.Area;

public class AreaDaoImpl implements AreaDao {
    @Override
    public void saveArea(Area area, EntityManager em) {
        try {
            em.getTransaction().begin();
            em.persist(area); // Salva nel database
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }
}
