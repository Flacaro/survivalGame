package model.entity.fight;

import model.entity.Attack;
import model.entity.Enemy;
import model.entity.Player;

public class Victory implements State{
    Fight fight;

    Player player=fight.getGame().getPlayer();
    Enemy enemy= fight.getEnemy();

    public Victory(Fight fight) {
        this.fight=fight;
    }

    @Override
    public void playerChooses(int choice) {

    }

    @Override
    public void playerFightsBack(Attack attack) {

    }

    @Override
    public void enemyFightsBack() {

    }

    @Override
    public void playerWins() {
        if (player.getExp()<100){
            player.setExp(player.getExp()+(10*enemy.getLevel()));
            if (player.getLevel()<10 && player.getExp()==100){
                player.setLevel(player.getLevel()+1);
                player.setExp(0);
            }
        }
        fight.getObserverUI().notifyVictory(player);
    }

    @Override
    public void playerLoses() {

    }
}
