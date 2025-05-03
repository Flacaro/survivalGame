package model.entity.fight;

import model.entity.*;

public class PlayerTurn implements State {

    Fight fight;

    Player player;
    Enemy enemy;

    public PlayerTurn(Fight fight) {
        this.fight=fight;
        this.player=fight.getGame().getPlayer();
        this.enemy= fight.getEnemy();
    }

    @Override
    public void playerChooses(int choice) {
        //viene richiamato dal controllore per modificare lo stato del
        //player, se sceglie di scappare modifica la posizione

        //choise=0 -> runaway
        //choise=1 -> fight
        boolean runaway=false;
        switch (choice){
            case 0:
                if (fight.getEnemy().getLevel()>=fight.getGame().getPlayer().getLevel()){
                    //comunicare al player che non può scappare
                    fight.getObserverUI().updateRunaway(runaway);
                }else{
                    //scegliere come modificare la posizione
                    runaway=true;
                    fight.getObserverUI().updateRunaway(runaway);
                }
            case 1:
                //ha scelto di combattere, deve scegliere la risorsa
                fight.getObserverUI().notifyFight();
        }

    }


    @Override
    public void playerFightsBack(Attack attack) {
        //il palyer deve combattere quindi deve selezionare una risorsa e un attacco
        //viene richiamato dal controllore quando l'esito della scelta è 1
        //riceve come parametro l'attacco
        if (player.getHealth()!=0){
            if (enemy.getHealth()!=0){
                if (enemy.getLevel()==player.getLevel()){
                    enemy.setHealth(enemy.getHealth()-attack.getDamage());
                    fight.getObserverUI().updateEnemy(enemy);
                } else if (enemy.getLevel()<player.getLevel()) {
                    //il danno dell'attacco varia in base al livello del nemico
                    //possiamo implementare una funzione che decide di quanto far
                    //aumentare l'attacco
                    enemy.setHealth(enemy.getHealth()-(attack.getDamage())*1.5);
                    System.out.println("attacco andato a buon fine "+ enemy.getHealth());
                    fight.getObserverUI().updateEnemy(enemy);
                }
            }
        }
        if (enemy.getHealth()==0 || enemy.getHealth()<0) {
            fight.playerWins();
        }else {
            fight.enemyFightsBack();
        }
    }

    @Override
    public void enemyFightsBack() {
        //implementato in EnemyTurn
    }

    @Override
    public void playerWins() {
        //implementato in Victory
    }

    @Override
    public void playerLoses() {
        //implementato in Defeat
    }
}
