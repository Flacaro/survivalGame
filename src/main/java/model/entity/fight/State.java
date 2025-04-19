package model.entity.fight;

import model.entity.Enemy;
import model.entity.Player;

public interface State {

    public void playerChooses();
    public void playerFightsBack();
    public void playerRunsAway();
    public void enemyFightsBack();
    public void playerWins();
    public void playerLoses();

}
