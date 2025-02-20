package org.example.persistence;

import jakarta.persistence.EntityManager;
import org.example.model.Map;

public interface MapDao {

    void saveMap(Map map, EntityManager em);
}
