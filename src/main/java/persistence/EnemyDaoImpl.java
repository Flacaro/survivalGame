package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.entity.Enemy;
import model.entity.Event;
import model.entity.SimpleResource;
import persistence.dao.EnemyDao;

import java.util.ArrayList;

public class EnemyDaoImpl implements EnemyDao {

    @Override
    public ArrayList<Enemy> getEnemies(EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            TypedQuery<Event> query = em.createQuery("SELECT e FROM Event e WHERE e.type='NEMICO'", Event.class);

            ArrayList<Enemy> enemies = new ArrayList<>();
            for (Event a : query.getResultList()) {
                enemies.add((Enemy) a);
            }
            return enemies;

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public Enemy getEnemyById(long id, EntityManager em) {
            TypedQuery<Enemy> query = em.createQuery("SELECT e FROM Enemy e where e.id =:id", Enemy.class);
            query.setParameter("id", id);
            return query.getSingleResult();
    }
}
