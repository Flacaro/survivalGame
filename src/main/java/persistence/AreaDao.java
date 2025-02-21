package persistence;

import jakarta.persistence.EntityManager;
import model.domain.AreaDomain;

import java.util.List;

public interface AreaDao {
    void saveTotalMapArea(List<AreaDomain> areaDomain, EntityManager em);
}
