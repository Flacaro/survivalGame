package model.entity.fight;

import model.entity.*;

import java.util.ArrayList;

public class Defeat implements State{

    Fight fight;

    Player player=fight.getGame().getPlayer();
    Enemy enemy= fight.getEnemy();

    public Defeat(Fight fight) {
        this.fight=fight;
    }
    @Override
    public void playerChooses(int choise) {

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
        inventory.remove((ArrayList<SimpleResource>) inventory.getResources());
        inventory.removeCraftedResource((ArrayList<CraftedResource>) inventory.getCraftedResourceList());
        fight.getObserverUI().notifyDefeat(player);
    }
}
