package Controller;

import jakarta.persistence.EntityManager;
import org.example.model.Area;
import org.example.model.Game;
import org.example.model.Map;
import org.example.model.Player;
import org.example.persistence.*;

public class DBController {


    private PlayerDaoImpl playerDaoImpl =new PlayerDaoImpl();
    private GameDao gameDao= new GameDao();
    private MapDaoImpl mapDaoImpl =new MapDaoImpl();
    private AreaDaoimpl areaDaoimpl =new AreaDaoimpl();

    public void insertPlayer(Player player){
        EntityManager em= EntityManagerSingleton.getEntityManager();
        playerDaoImpl.savePlayer(player,em);
        close();
    }
    public void insertGame(Game game){
        EntityManager em= EntityManagerSingleton.getEntityManager();
        gameDao.saveGame(game,em);
        close();
    }
    public void insertMap(Map map){
        EntityManager em= EntityManagerSingleton.getEntityManager();
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
