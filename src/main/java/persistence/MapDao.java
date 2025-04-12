package persistence;

import jakarta.persistence.EntityManager;
import model.entity.Map;
import model.entity.SimpleResource;

public interface MapDao {

    void updateMap(Map mapDomain, SimpleResource res, EntityManager em);

    void saveMap(Map map, EntityManager em);
}
