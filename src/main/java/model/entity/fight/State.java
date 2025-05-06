package model.entity.fight;

import model.entity.Attack;
import model.entity.SimpleResource;

import java.io.IOException;

public interface State {

    public void playerChooses(int choice) throws IOException;
    public void playerFightsBack(Attack attack) throws IOException;
    public void enemyFightsBack() throws IOException;
    public void playerWins();
    public void playerLoses();

}
