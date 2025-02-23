package persistence;

import jakarta.persistence.EntityManager;
import model.domain.AreaDomain;
import services.AreaService;

import java.util.List;

public class AreaDaoImpl implements AreaDao {

    @Override
    public void saveTotalMapArea(List<AreaDomain> areaDomain, EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            AreaService areaService=new AreaService();
            for (AreaDomain value : areaDomain) {
                em.persist(areaService.areaMapper(value));
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }



}
