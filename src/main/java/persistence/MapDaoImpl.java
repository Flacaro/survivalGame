package persistence;

import jakarta.persistence.EntityManager;
import model.domain.MapDomain;
import services.MapServices;

public class MapDaoImpl implements MapDao {

    public void saveMap(MapDomain map, EntityManager em) {
        try {
            MapServices mapServices= new MapServices();
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


}
