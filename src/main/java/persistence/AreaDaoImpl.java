package persistence;

import jakarta.persistence.EntityManager;
import model.domain.AreaDomain;
import java.util.List;

public class AreaDaoImpl implements AreaDao {

    @Override
    public void saveTotalMapArea(List<AreaDomain> areaDomain, EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            for (AreaDomain value : areaDomain) {
                em.persist(value);
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }



}
