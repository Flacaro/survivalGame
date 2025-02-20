package controller;

import jakarta.persistence.EntityManager;
import model.*;
import persistence.*;

import java.util.ArrayList;

public class DBController {

    private final EntityManager em = EntityManagerSingleton.getEntityManager();

    private final PlayerDaoImpl playerDaoImpl = new PlayerDaoImpl();
    private final GameDaoImpl gameDaoImpl = new GameDaoImpl();
    private final MapDaoImpl mapDaoImpl = new MapDaoImpl();
    private final AreaDaoImpl areaDaoImpl = new AreaDaoImpl();

    public void checkBeginTransaction() {
        if(!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

//    public void setTotalMapArea(Map map) {
//        checkBeginTransaction();
//        areaDaoImpl.saveTotalMapArea(map.getTotalMapArea(), em);
//        close();
//    }


    public void insertPlayer(Player player){
        checkBeginTransaction();
        if (player.getGame() != null && player.getGame().getId() == 0L) {
            gameDaoImpl.saveGame(player.getGame(), em);  // Salva il gioco prima del player
        }

        playerDaoImpl.savePlayer(player, em);
        close();
    }

//    public void insertPlayer(Player player){
//        checkBeginTransaction();
//        playerDaoImpl.savePlayer(player,em);
//        close();
//    }

    public void insertMap(Map map){
        checkBeginTransaction();
        Mode m = new Mode(1L);
        map.setTotalMapArea(m, map);
        mapDaoImpl.saveMap(map,em);
        close();
    }
    public void insertArea(ArrayList<Area> area){
        checkBeginTransaction();
        areaDaoImpl.saveTotalMapArea(area,em);
        close();
    }

    public void setAdjacentArea(ArrayList<Area> adjacentArea) {
        checkBeginTransaction();

    }

    public void close(){
       EntityManagerSingleton.closeEntityManager();
    }
}
