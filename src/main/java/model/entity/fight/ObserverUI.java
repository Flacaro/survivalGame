package model.entity.fight;

import model.entity.Enemy;
import model.entity.Player;

public class ObserverUI extends Observer{

    private State state;

    @Override
    public void update(State state) {
        state=state;
    }

    @Override
    public State getState() {
        return state;
    }


    //quando si va a creare l'oggetto Fight poi va richiamato il metodo playerChoses(int choise)
    //che in base alla selezione implementa la fuga o il combattimento

    @Override
    public void updateRunaway(Boolean result) {
        //richiama il controllore che gestisce questo caso
        //per result=true -> il player può scappare
        //(decidere come aggiornare la posizione)
        //result=false -> combatte
        //(far scegliere la risorsa e chiamare playerFightsBack(SimpleResource sr)

    }

    @Override
    public void updateEnemy(Enemy enemy) {
        //nel parametro c'è l'enemy dopo che ha subito l'attacco dal giocatore
        //richiamare il controllore per far mostrare alla view la salute
        //danneggiata del nemico
    }

    @Override
    public void notifyVictory(Player player) {
        //nel parametro c'è il giocatore dopo che ha vinto il combattimento
        //modificati punti exp e livello
        //mostrarli al giocatore ed andare avanti
    }

    @Override
    public void notifyDefeat(Player player) {
        //nel parametro c'è il player dopo essere stato sconfitto
        //salute a 0
        //inventario vuoto
        //notificarlo al giocatore aggiornare il db e interrompere la partita
    }

    @Override
    public void playersTurn(Player player) {
        //nel paramentro c'è il player con la salute aggiornata dopo l'attacco
        //del nemico, chiedere al giocatore cosa vuole fare e richiamare in caso di attacco
        //ripetuto i metodi playerFightsBack(SimpleResource sr) o playerChoses(int choise)
    }
}
