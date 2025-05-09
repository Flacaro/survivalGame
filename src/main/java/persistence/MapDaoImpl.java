package persistence;

import jakarta.persistence.EntityManager;
import model.entity.Area;
import model.entity.Enemy;
import model.entity.Map;
import model.entity.SimpleResource;
import persistence.dao.MapDao;

import java.util.Objects;


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
    public void updateMap(Map mapDomain, SimpleResource res, Enemy enemy, EntityManager em ) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }

            Map map = em.find(Map.class, mapDomain.getId());
            if (map != null) {
                for (Area area : map.getAreas()) {
                    if (res != null && Objects.equals(area.getIdEvent().getId(), res.getId())) {
                        //eliminare la risorsa nell'area
                        area.setIdEvent(null);
                        area.setCategory(null);
                        em.merge(map);
                        break;
                    } else if (enemy != null && Objects.equals(area.getIdEvent().getId(), enemy.getId())) {
                        area.setIdEvent(null);
                        area.setCategory(null);
                        em.merge(map);
                        break;
                    }
                }
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }


}
