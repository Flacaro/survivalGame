package persistence.dao;

import jakarta.persistence.EntityManager;
import model.entity.Mode;

public interface ModeDao {

    public Mode getModeById(long id, EntityManager em);
}
