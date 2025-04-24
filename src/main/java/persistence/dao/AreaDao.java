package persistence.dao;

import jakarta.persistence.EntityManager;
import model.entity.Area;

import java.util.ArrayList;
import java.util.List;

public interface AreaDao {

    void saveTotalMapArea(List<Area> areaDomain, EntityManager em);

    void updateArea(List<Area> areas, EntityManager em);

    ArrayList<Area> getAreas(EntityManager em);

    Area getAreaById(EntityManager em, long idArea);
}
