package persistence;

import jakarta.persistence.EntityManager;
import model.Area;
import java.util.List;

public class AreaDaoImpl implements AreaDao {

    @Override
    public void saveTotalMapArea(List<Area> area, EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            for (Area value : area) {
                em.persist(value);
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }



}
