package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.domain.AreaDomain;
import model.domain.ResourceDomain;
import model.entity.Area;
import model.entity.Resource;
import services.AreaService;

import java.util.ArrayList;
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

    @Override
    public void updateArea(List<AreaDomain> areas, EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            for (AreaDomain value : areas) {
                Area area = em.find(Area.class, value.getId()); // Trova l'oggetto con ID 1
                if (area != null) {
                    area.setIdEvent(value.getIdEvent());
                    em.getTransaction().commit();// Modifica i campi necessari
                }
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

    }

    @Override
    public ArrayList<AreaDomain> getAreas(EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            AreaService areaService= new AreaService();
            TypedQuery<Area> query = em.createQuery("SELECT a FROM Area a", Area.class);

            ArrayList<AreaDomain> areaDomains= new ArrayList<>();
            for (Area a :query.getResultList()){
                areaDomains.add(areaService.areaDomainMapper(a));
            }
            return areaDomains;

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return  null;
    }


}
