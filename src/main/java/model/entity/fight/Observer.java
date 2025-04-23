package model.entity.fight;

import model.entity.Enemy;
import model.entity.Player;

public abstract class Observer {
    public abstract void update(State state);
    public abstract State getState();

    public abstract void updateRunaway(Boolean result);

    public abstract void updateEnemy(Enemy enemy);
    public abstract void notifyVictory(Player player);
    public abstract void notifyDefeat(Player player);
    public  abstract void playersTurn(Player player);
}
