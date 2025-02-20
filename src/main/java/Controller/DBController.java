package Controller;

import jakarta.persistence.EntityManager;
import org.example.model.*;
import org.example.persistence.*;

public class DBController {


    private final PlayerDaoImpl playerDaoImpl = new PlayerDaoImpl();
    private final GameDaoImpl gameDaoImpl = new GameDaoImpl();
    private final MapDaoImpl mapDaoImpl = new MapDaoImpl();
    private final AreaDaoimpl areaDaoimpl = new AreaDaoimpl();

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
        map.setTotalMapArea(m);
        mapDaoImpl.saveMap(map,em);
        close();
    }
    public void insertArea(Area area){
        EntityManager em= EntityManagerSingleton.getEntityManager();
        areaDaoimpl.saveArea(area,em);
        close();
    }

    public void close(){
       EntityManagerSingleton.closeEntityManager();
    }
}
