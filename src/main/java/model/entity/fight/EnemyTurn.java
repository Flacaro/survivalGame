package model.entity.fight;

import model.entity.Attack;
import model.entity.Enemy;
import model.entity.Player;

import java.io.IOException;
import java.util.List;

public class EnemyTurn implements State {
    Fight fight;

    Player player;
    Enemy enemy;

    public EnemyTurn(Fight fight) {
        this.fight = fight;
        this.player = fight.getGame().getPlayer();
        this.enemy = fight.getEnemy();
    }

    @Override
    public void playerChooses(int choice) {

    }

    @Override
    public void playerFightsBack(Attack attack) {
    }

    @Override
    public void enemyFightsBack() throws IOException {
        List<Attack> attacks = enemy.getAttacks();
        //implementare una funzione che sceglie l'attacco del nemico
        Attack attack = attacks.get(0);
        if (enemy.getHealth() > 0.1) {
            if (player.getHealth() > 0.1) {
                player.setHealth(player.getHealth() - attack.getDamage());
            }
        }
        if (player.getHealth() > 0.1) {
            fight.getObserverUI().playersTurn(player);
        }
    }

    @Override
    public void playerWins() {

    }

    @Override
    public void playerLoses() {

    }
}
