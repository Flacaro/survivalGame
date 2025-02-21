package persistence;

import jakarta.persistence.EntityManager;
import model.domain.Area;

import java.util.List;

public interface AreaDao {
    void saveTotalMapArea(List<Area> area, EntityManager em);
}
