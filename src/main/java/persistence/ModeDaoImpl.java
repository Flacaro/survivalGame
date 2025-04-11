package persistence;

import jakarta.persistence.EntityManager;
import model.entity.Mode;

public class ModeDaoImpl implements ModeDao{

    @Override
    public Mode getModeById(long id, EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            Mode area = em.find(Mode.class, id); // Trova l'oggetto con ID 1
            return area;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return null;
    }

}
