package model.entity.fight;

import model.entity.Enemy;
import model.entity.Player;

import java.io.IOException;

public abstract class Observer {
    public abstract void notifyFight() throws IOException;

    public abstract void updateRunaway(Boolean result) throws IOException;

    public abstract void updateEnemy(Enemy enemy);
    public abstract void notifyVictory(Player player);
    public abstract void notifyDefeat(Player player);
    public  abstract void playersTurn(Player player) throws IOException;
}
