package model.entity.fight;

import model.entity.Attack;
import model.entity.Enemy;
import model.entity.Game;

public class Fight {
    private EnemyTurn  enemyTurn;
    private PlayerTurn playerTurn;
    private StandBy standBy;
    private Victory victory;
    private Defeat defeat;

    private State currentState;
    private Observer observerUI;

    private Game game;
    private Enemy enemy;

    public Fight(Game game, Enemy enemy) {
        this.game= game;
        this.enemy=enemy;
        this.enemyTurn= new EnemyTurn(this);
        this.playerTurn=new PlayerTurn(this);
        this.standBy=new StandBy(this);
        this.victory=new Victory(this);
        this.defeat=new Defeat(this);
        this.currentState=standBy;


    }

    public Fight() {

    }

    public void standBy(){
        currentState=standBy;
    }

    public void playerFightsBack(Attack attack){
        currentState.playerFightsBack(attack);
    }

    public void enemyFightsBack(){
        currentState=enemyTurn;
        currentState.enemyFightsBack();
    }


    public void playerChoses(int choice){
        currentState=playerTurn;
        currentState.playerChooses(choice);
    }

    public void playerWins(){
        currentState=victory;
        currentState.playerWins();
        standBy();
    }

    public void playerLoses(){
        currentState=defeat;
        currentState.playerLoses();
        standBy();
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Observer getObserverUI() {
        return observerUI;
    }

    public void setObserverUI(Observer observerUI) {
        this.observerUI = observerUI;
    }
}
