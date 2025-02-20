package persistence;

import jakarta.persistence.EntityManager;
import model.Map;

public interface MapDao {

    void saveMap(Map map, EntityManager em);
}
