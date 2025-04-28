package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.entity.Enemy;
import persistence.dao.EnemyDao;

import java.util.ArrayList;

public class EnemyDaoImpl implements EnemyDao {

    @Override
    public ArrayList<Enemy> getEnemies(EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            TypedQuery<Enemy> query = em.createQuery("SELECT e FROM Enemy e", Enemy.class);

            ArrayList<Enemy> enemies = new ArrayList<>();
            for (Enemy a : query.getResultList()) {
                enemies.add(a);
            }
            return enemies;

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return null;
    }
}
