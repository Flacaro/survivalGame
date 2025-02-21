package controller;

import jakarta.persistence.EntityManager;
import model.*;
import persistence.*;

import java.util.ArrayList;
import java.util.List;

public class DBController {


    private final PlayerDaoImpl playerDaoImpl = new PlayerDaoImpl();
    private final GameDaoImpl gameDaoImpl = new GameDaoImpl();
    private final MapDaoImpl mapDaoImpl = new MapDaoImpl();
    private final AreaDaoImpl areaDaoImpl = new AreaDaoImpl();

    public void insertPlayer(Player player){
        EntityManager em= EntityManagerSingleton.getEntityManager();
        playerDaoImpl.savePlayer(player,em);
        close();
    }
    public void insertGame(Game game){
        EntityManager em= EntityManagerSingleton.getEntityManager();
        gameDaoImpl.saveGame(game,em);
        close();
    }
    public void insertMap(Map map){
        EntityManager em= EntityManagerSingleton.getEntityManager();
        Mode m = new Mode(1L);
        map.setTotalMapArea(m, map);
        mapDaoImpl.saveMap(map,em);
        close();
    }
    public void insertArea(List<Area> area){
        EntityManager em= EntityManagerSingleton.getEntityManager();
        areaDaoImpl.saveTotalMapArea(area,em);
        close();
    }

    public void close(){
       EntityManagerSingleton.closeEntityManager();
    }
}
