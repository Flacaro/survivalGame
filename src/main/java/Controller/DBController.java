package Controller;

import org.example.model.Game;
import org.example.model.Map;
import org.example.model.Player;
import org.example.persistence.GameDao;
import org.example.persistence.MapDao;
import org.example.persistence.PlayerDao;

public class DBController {

    private PlayerDao playerDao;
    private GameDao gameDao;
    private MapDao mapDao;

    public void insertPlayer(Player player){
        playerDao.savePlayer(player);
        playerDao.close();
    }
    public void insertGame(Game game){
        gameDao.saveGame(game);
        gameDao.close();
    }
    public void insertMap(Map map){
        mapDao.saveMap(map);
        mapDao.close();
    }
}
