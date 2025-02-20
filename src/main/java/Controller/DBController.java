package Controller;

import org.example.model.Game;
import org.example.model.Map;
import org.example.model.Player;
import org.example.persistence.*;

public class DBController {

    private final PlayerDaoImpl playerDaoImpl = new PlayerDaoImpl();
    private final GameDaoImpl gameDaoImpl = new GameDaoImpl();
    private final MapDaoImpl mapDaoImpl = new MapDaoImpl();

    public void insertPlayerGameMap(Player player, Game game, Map map){
        playerDaoImpl.savePlayer(player);
        mapDaoImpl.saveMap(map);
        gameDaoImpl.saveGame(game);
    }
//    public void insertGame(Game game){
//        gameDaoImpl.saveGame(game);
////        gameDaoImpl.close();
//    }
//    public void insertMap(Map map){
//        mapDaoImpl.saveMap(map);
////        mapDaoImpl.close();
//    }
}
