package model.entity.fight;

import model.entity.Enemy;
import model.entity.Player;

public class Fight {
    private EnemyTurn  enemyTurn;
    private PlayerTurn playerTurn;
    private StandBy standBy;
    private Victory victory;
    private State currentState;


    private Observer observerUI;

    public Fight() {
        this.enemyTurn= new EnemyTurn(this);
        this.playerTurn=new PlayerTurn(this);
        this.standBy=new StandBy(this);
        this.victory=new Victory(this);
        this.currentState=standBy;
        observerUI=new ObserverUI();
    }

    public void playerFightsBack(){
        currentState.playerFightsBack();
        observerUI.update(currentState);
    }
    public void playerRunsAway(){
        currentState.playerRunsAway();
        observerUI.update(currentState);
    }
    public void enemyFightsBack(){
        currentState.enemyFightsBack();
        observerUI.update(currentState);
    }
    public void playerWins(){
        currentState.playerWins();
        observerUI.update(currentState);
    }
    public void playerLoses(){
        currentState.playerLoses();
        observerUI.update(currentState);
    }

    public void playerChoses(){
        currentState.playerChooses();
        observerUI.update(currentState);
    }

}
