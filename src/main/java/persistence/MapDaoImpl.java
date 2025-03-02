package persistence;

import jakarta.persistence.EntityManager;
import model.domain.InventoryDomain;
import model.domain.MapDomain;
import model.domain.ResourceDomain;
import model.entity.Area;
import model.entity.Inventory;
import model.entity.Map;
import model.entity.Resource;
import services.MapServices;
import services.ResourceService;

import java.util.List;

public class MapDaoImpl implements MapDao {

    public void saveMap(MapDomain map, EntityManager em) {
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

    public void updateMap(MapDomain mapDomain, ResourceDomain res, EntityManager em) {
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
                        break;
                    }
                }

                em.merge(map);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }


}
