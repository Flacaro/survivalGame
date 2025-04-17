package model.entity.fight;

import model.entity.Enemy;
import model.entity.Player;

public class Fight {
    private EnemyTurn  enemyTurn;
    private PlayerTurn playerTurn;
    private StandBy standBy;
    private Victory victory;

    private State currentState;

    private Enemy enemy;
    private Player player;

    private Observer observerUI;

    public Fight(Enemy enemy, Player player) {
        this.enemyTurn= new EnemyTurn(this);
        this.playerTurn=new PlayerTurn(this);
        this.standBy=new StandBy(this);
        this.victory=new Victory(this);
        this.currentState=standBy;
        this.enemy=enemy;
        this.player=player;
        observerUI=new ObserverUI();
    }

    public void playerFightsBack(Player player){
        currentState.playerFightsBack(player);
        observerUI.update(currentState);
    }
    public void playerRunsAway(Player player){
        currentState.playerRunsAway(player);
        observerUI.update(currentState);
    }
    public void enemyFightsBack(Enemy enemy){
        currentState.enemyFightsBack(enemy);
        observerUI.update(currentState);
    }
    public void playerWins(Player player){
        currentState.playerWins(player);
        observerUI.update(currentState);
    }
    public void playerLoses(Player player){
        currentState.playerLoses(player);
        observerUI.update(currentState);
    }

}
