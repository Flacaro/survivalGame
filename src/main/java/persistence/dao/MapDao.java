package persistence.dao;

import jakarta.persistence.EntityManager;
import model.entity.Enemy;
import model.entity.Map;
import model.entity.SimpleResource;

public interface MapDao {

    void updateMap(Map mapDomain, SimpleResource res, Enemy enemy, EntityManager em);

    void saveMap(Map map, EntityManager em);
}
