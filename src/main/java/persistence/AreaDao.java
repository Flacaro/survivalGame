package persistence;

import jakarta.persistence.EntityManager;
import model.Area;

import java.util.ArrayList;
import java.util.List;

public interface AreaDao {
    void saveTotalMapArea(List<Area> area, EntityManager em);
}
