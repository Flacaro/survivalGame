package model.entity.fight;

public class StandBy implements State {
    Fight fight;

    public StandBy(Fight fight) {
        this.fight=fight;
    }

    @Override
    public void playerChooses() {
        //controllore che richiama la view e che chiede al giocatore per poi utilizzare questo metodo
        //ed aggiornare gli stati
    }

    @Override
    public void playerFightsBack() {
        //il controllore richiama il giocatore che deve scegliere l'arma da usare
    }

    @Override
    public void playerRunsAway() {

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
