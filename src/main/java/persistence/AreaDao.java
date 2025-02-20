package persistence;

import jakarta.persistence.EntityManager;
import model.Area;

import java.util.ArrayList;

public interface AreaDao {
    void saveTotalMapArea(ArrayList<Area> area, EntityManager em);
}
