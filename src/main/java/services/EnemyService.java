package services;

import model.domain.EnemyDomain;
import model.entity.Enemy;

public class EnemyService {
    public Enemy enemyMapper(EnemyDomain enemyDomain){
        Enemy enemy= new Enemy();
        enemy.setId(enemyDomain.getId());
        enemy.setAttacks(enemyDomain.getAttacks());
        enemy.setLevel(enemyDomain.getLevel());
        enemy.setName(enemyDomain.getName());
        enemy.setDescription(enemyDomain.getDescription());
        enemy.setType(enemyDomain.getType());
        return enemy;
    }

    public EnemyDomain enemyDomainMapper(Enemy enemy){
        EnemyDomain enemyDomain= new EnemyDomain();
        enemyDomain.setId(enemy.getId());
        enemyDomain.setAttacks(enemy.getAttacks());
        enemyDomain.setLevel(enemy.getLevel());
        enemyDomain.setName(enemy.getName());
        enemyDomain.setDescription(enemy.getDescription());
        enemyDomain.setType(enemy.getType());
        return enemyDomain;
    }

    public EnemyDomain getEnemyById(long id) {
        return null;
    }
}
