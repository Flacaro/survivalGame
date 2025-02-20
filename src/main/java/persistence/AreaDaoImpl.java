package persistence;

import jakarta.persistence.EntityManager;
import model.Area;

import java.util.ArrayList;

public class AreaDaoImpl implements AreaDao {

    @Override
    public void saveTotalMapArea(ArrayList<Area> area, EntityManager em) {
        try {
            em.getTransaction().begin();
            for (int i = 0; i < area.size(); i++) {
                em.persist(area);
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

}
