package model.entity.fight;


public class Victory implements State {
    Fight fight;

    public Victory(Fight fight) {
        this.fight=fight;
    }

    @Override
    public void playerChooses() {
    }

    @Override
    public void playerFightsBack() {

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
