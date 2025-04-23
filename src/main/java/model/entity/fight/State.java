package model.entity.fight;

import model.entity.Attack;
import model.entity.SimpleResource;

public interface State {

    public void playerChooses(int choise);
    public void playerFightsBack(Attack attack);
    public void enemyFightsBack();
    public void playerWins();
    public void playerLoses();

}
