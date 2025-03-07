package persistence;

import jakarta.persistence.EntityManager;
import model.domain.MapDomain;
import model.domain.ResourceDomain;
import model.entity.Area;
import model.entity.Map;
import services.MapServices;


public class MapDaoImpl implements MapDao {

    @Override
    public void saveMap(MapDomain map) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        try {
            MapServices mapServices = new MapServices();
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            em.persist(mapServices.mapMapper(map));
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

    }

    @Override
    public void updateMap(MapDomain mapDomain, ResourceDomain res) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
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
