package model.entity.fight;

import model.entity.Enemy;
import model.entity.Player;

public class Victory implements State {
    Fight fight;

    public Victory(Fight fight) {
        this.fight=fight;
    }

    @Override
    public void playerFightsBack(Player player) {

    }

    @Override
    public void playerRunsAway(Player player) {

    }

    @Override
    public void enemyFightsBack(Enemy enemy) {

    }

    @Override
    public void playerWins(Player player) {

    }

    @Override
    public void playerLoses(Player player) {

    }
}
