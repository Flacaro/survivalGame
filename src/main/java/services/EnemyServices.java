package services;

import model.domain.EnemyDomain;
import model.entity.Enemy;

public class EnemyServices {
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
}
