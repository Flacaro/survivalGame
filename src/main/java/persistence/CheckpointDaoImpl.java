package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.entity.Checkpoint;
import persistence.dao.CheckpointDao;

import java.util.ArrayList;
import java.util.List;

public class CheckpointDaoImpl implements CheckpointDao {

    @Override
    public void saveCheckpoint(Checkpoint ck, EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            em.merge(ck);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public List<Checkpoint> getCheckpoints(EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            TypedQuery<Checkpoint> query = em.createQuery("SELECT c FROM Checkpoint c", Checkpoint.class);

            ArrayList<Checkpoint> checkpoints = new ArrayList<>();
            for (Checkpoint c : query.getResultList()) {
                checkpoints.add(c);
            }
            return checkpoints;

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return null;
    }
}
