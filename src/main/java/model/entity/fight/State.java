package model.entity.fight;

import model.entity.Enemy;
import model.entity.Player;

public interface State {

    public void playerFightsBack(Player player);
    public void playerRunsAway(Player player);
    public void enemyFightsBack(Enemy enemy);
    public void playerWins(Player player);
    public void playerLoses(Player player);

}
