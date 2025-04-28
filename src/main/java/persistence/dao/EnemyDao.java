package persistence.dao;

import jakarta.persistence.EntityManager;
import model.entity.Enemy;

import java.util.ArrayList;

public interface EnemyDao {

    ArrayList<Enemy> getEnemies(EntityManager em);
}
