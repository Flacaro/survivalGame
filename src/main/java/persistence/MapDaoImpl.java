package persistence;

import jakarta.persistence.EntityManager;
import model.entity.Area;
import model.entity.Map;
import model.entity.Resource;
import model.entity.SimpleResource;


public class MapDaoImpl implements MapDao {

    @Override
    public void saveMap(Map map,EntityManager em) {
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

    @Override
    public void updateMap(Map mapDomain, SimpleResource res, EntityManager em ) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }

            Map map = em.find(Map.class, mapDomain.getId());
            if (map != null) {
                for (Area area : map.getAreas()) {
                    if (area.getIdEvent() == res.getId()) {
                        //eliminare la risorsa nell'area
                        area.setIdEvent(0L);
                        area.setCategory(null);
                        em.merge(map);
                        break;
                    }
                }
                //em.merge(map);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }


}
