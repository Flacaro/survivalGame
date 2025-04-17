package model.entity.fight;

import model.entity.Enemy;
import model.entity.Player;

public class Fight {
    public EnemyTurn  enemyTurn;
    public PlayerTurn playerTurn;
    public StandBy standBy;
    public Victory victory;

    public State currentState;

    public Enemy enemy;
    public Player player;

    public Fight(Enemy enemy, Player player) {
        this.enemyTurn= new EnemyTurn(this);
        this.playerTurn=new PlayerTurn(this);
        this.standBy=new StandBy(this);
        this.victory=new Victory(this);
        this.currentState=standBy;
        this.enemy=enemy;
        this.player=player;
    }

    public void playerFightsBack(Player player){
        currentState.playerFightsBack(player);
    }
    public void playerRunsAway(Player player){
        currentState.playerRunsAway(player);
    }
    public void enemyFightsBack(Enemy enemy){
        currentState.enemyFightsBack(enemy);
    }
    public void playerWins(Player player){
        currentState.playerWins(player);
    }
    public void playerLoses(Player player){
        currentState.playerLoses(player);
    }
}
