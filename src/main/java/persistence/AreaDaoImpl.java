package persistence;

import jakarta.persistence.EntityManager;
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
            AreaService areaService=new AreaService();
            for (AreaDomain value : areas) {

                em.persist(areaService.areaMapper(value));
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

    }

    @Override
    public void getArea(EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            AreaService areaService= new AreaService();
            TypedQuery<Area> query = em.createQuery("SELECT a FROM Area a WHERE ", Area.class);

            ArrayList<AreaDomain> areaDomains= new ArrayList<>();
            for (Area a :query.getResultList()){
                areaDomains.add(areaService.areaDomainMapper(a));
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }


    }


}
