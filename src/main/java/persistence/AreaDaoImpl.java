package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.entity.Area;
import persistence.dao.AreaDao;

import java.util.ArrayList;
import java.util.List;

public class AreaDaoImpl implements AreaDao {

    @Override
    public void saveTotalMapArea(List<Area> areaDomain, EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            for (Area value : areaDomain) {
                em.persist(value);
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public void updateArea(List<Area> areas, EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            for (Area value : areas) {
                Area area = em.find(Area.class, value.getId()); // Trova l'oggetto con ID 1
                if (area != null) {
                    area.setIdEvent(value.getIdEvent());
                    area.setCategory(value.getCategory());
                }
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

    }

    @Override
    public ArrayList<Area> getAreas(EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            TypedQuery<Area> query = em.createQuery("SELECT a FROM Area a", Area.class);

            ArrayList<Area> areaDomains= new ArrayList<>();
            for (Area a :query.getResultList()){
                areaDomains.add(a);
            }
            return areaDomains;

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return  null;
    }

    @Override
    public Area getAreaById(EntityManager em, long idArea) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            Area area = em.find(Area.class, idArea); // Trova l'oggetto con ID 1
            return area;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
return null;
    }


}
