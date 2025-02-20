package persistence;

import jakarta.persistence.EntityManager;
import model.Area;

public interface AreaDao {
    public void saveArea(Area area, EntityManager em);
}
