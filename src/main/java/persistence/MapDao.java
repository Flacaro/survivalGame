package persistence;

import jakarta.persistence.EntityManager;
import model.entity.Map;

public interface MapDao {

    void saveMap(Map map, EntityManager em);
}
