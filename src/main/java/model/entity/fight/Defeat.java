package model.entity.fight;

import model.entity.*;

import java.util.ArrayList;
import java.util.List;

public class Defeat implements State{

    Fight fight;

    Player player;
    Enemy enemy;

    public Defeat(Fight fight) {
        this.fight=fight;
        this.player=fight.getGame().getPlayer();
        this.enemy= fight.getEnemy();
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

    }

    @Override
    public void playerLoses() {
        Inventory inventory= player.getInventory();
        inventory.setResources(null);
        inventory.setCraftedResourceList(null);
        player.setHealth(0.0);
        fight.standBy();
        fight.getObserverUI().notifyDefeat(player);
    }
}
