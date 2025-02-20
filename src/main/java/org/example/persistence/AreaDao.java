package org.example.persistence;

import jakarta.persistence.EntityManager;
import org.example.model.Area;

public interface AreaDao {
    public void saveArea(Area area, EntityManager em);
}
