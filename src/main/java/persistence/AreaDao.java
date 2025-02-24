package persistence;

import jakarta.persistence.EntityManager;
import model.domain.AreaDomain;

import java.util.ArrayList;
import java.util.List;

public interface AreaDao {
    void saveTotalMapArea(List<AreaDomain> areaDomain, EntityManager em);

    void updateArea(List<AreaDomain> areas, EntityManager em);

    ArrayList<AreaDomain> getArea(EntityManager em);
}
