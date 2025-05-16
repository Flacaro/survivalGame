package persistence.dao;

import jakarta.persistence.EntityManager;
import model.entity.Checkpoint;

import java.util.List;

public interface CheckpointDao {

    void saveCheckpoint(Checkpoint ck, EntityManager em);

    List<Checkpoint> getCheckpoints(EntityManager em);
}
