package persistence;

import jakarta.persistence.EntityManager;
import model.entity.Map;
import model.entity.Resource;

public interface MapDao {

    void updateMap(Map mapDomain, Resource res, EntityManager em);

    void saveMap(Map map, EntityManager em);
}
