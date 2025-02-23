package persistence;

import jakarta.persistence.EntityManager;
import model.domain.MapDomain;
import model.entity.Map;

public interface MapDao {

    void saveMap(MapDomain map, EntityManager em);
}
