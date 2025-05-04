package controller;

import model.entity.Attack;
import model.entity.Enemy;
import model.entity.Player;
import model.entity.fight.Fight;
import model.entity.fight.Observer;
import view.FightView;

import java.io.IOException;

public class FightController extends Observer {

    private Fight fight;
    private FightView fightView=new FightView();

    public FightController() {
    }

    public void startFight() throws IOException {
        fightView.displayFoundEnemy(fight.getEnemy().getName());
        int choice=fightView.getFightChoice();
        fight.playerChoses(choice);
    }

    public void choseWeapons(){
        System.out.println("Scegli l'arma");
        Attack attack=fightView.displayWeapons(fight);
        fight.playerFightsBack(attack);
    }

    public void setFight(Fight fight) {
        this.fight=fight;
    }

    @Override
    public void notifyFight() {
        choseWeapons();
    }

    @Override
    public void updateRunaway(Boolean result) {
        if (result){
            System.out.println("Sei riuscito a scappare");
        }
        else {
            System.out.println("Non sei riuscito a scappare");
            choseWeapons();
        }

    }

    @Override
    public void updateEnemy(Enemy enemy) {
        //nel parametro c'è l'enemy dopo che ha subito l'attacco dal giocatore
        //richiamare il controllore per far mostrare alla view la salute
        //danneggiata del nemico
        System.out.println("attacco andato a buon fine.Il nemico ha "+ enemy.getHealth()+ "di vita");
    }

    @Override
    public void notifyVictory(Player player) {
        //nel parametro c'è il giocatore dopo che ha vinto il combattimento
        //modificati punti exp e livello
        //mostrarli al giocatore ed andare avanti
        System.out.println("Hai sconfitto il  nemico");
    }

    @Override
    public void notifyDefeat(Player player) {
        //nel parametro c'è il player dopo essere stato sconfitto
        //salute a 0
        //inventario vuoto

        System.out.println("Il nemico ti ha sconfitto");
        //notificarlo al giocatore aggiornare il db e interrompere la partita


    }

    @Override
    public void playersTurn(Player player) {
        //nel paramentro c'è il player con la salute aggiornata dopo l'attacco
        //del nemico, chiedere al giocatore cosa vuole fare e richiamare in caso di attacco
        //ripetuto i metodi playerFightsBack(SimpleResource sr) o playerChoses(int choice)
        System.out.println("Hai subito un attacco.Ora hai "+ player.getHealth()+ "di vita");
        choseWeapons();

    }
    //chiede gli input alla view
    //passa gli input al fight
    //tramite l'observer aggiorna la view


}
