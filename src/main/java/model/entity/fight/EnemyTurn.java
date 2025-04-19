package model.entity.fight;

import model.entity.Enemy;
import model.entity.Player;

public class EnemyTurn implements State {
    Fight fight;

    public EnemyTurn(Fight fight) {
        this.fight=fight;
    }

    @Override
    public void playerChooses() {

    }

    @Override
    public void playerFightsBack() {
        //implementare il meto
    }

    @Override
    public void playerRunsAway() {

    }

    @Override
    public void enemyFightsBack() {

    }

    @Override
    public void playerWins() {

    }

    @Override
    public void playerLoses() {

    }
}
