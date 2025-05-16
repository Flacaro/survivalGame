package model.entity.fight;

import model.entity.Attack;

import java.io.IOException;

public interface State {

    void playerChooses(int choice) throws IOException;

    void playerFightsBack(Attack attack) throws IOException;

    void enemyFightsBack() throws IOException;

    void playerWins();

    void playerLoses();

}
