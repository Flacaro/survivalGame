package model.entity.fight;

import model.entity.Attack;

public class StandBy implements State {
    Fight fight;

    public StandBy(Fight fight) {
        this.fight=fight;
    }

    @Override
    public void playerChooses(int choise) {
        //controllore che richiama la view e che chiede al giocatore per poi utilizzare questo metodo
        //ed aggiornare gli stati
    }

    @Override
    public void playerFightsBack(Attack attack) {
        //il controllore richiama il giocatore che deve scegliere l'arma da usare
    }

    @Override
    public void enemyFightsBack() {

    }

    @Override
    public void playerWins() {

    }

    @Override
    public void playerLoses() {

    }
}
