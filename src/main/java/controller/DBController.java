package controller;

import jakarta.persistence.EntityManager;
import model.domain.AreaDomain;
import model.domain.GameDomain;
import model.domain.PlayerDomain;
import persistence.*;
import services.AreaService;
import services.GameService;
import java.util.ArrayList;
import java.util.List;


public class DBController {

    private final AreaService areaService = new AreaService();
    private final GameService gameService = new GameService();


    public void insertGame(GameDomain g) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        gameService.saveGame(g, em);
        close();
    }

    public void close() {
        EntityManagerSingleton.closeEntityManager();
    }

    public void updateArea(List<AreaDomain> areas) {
        AreaService areaService = new AreaService();
        EntityManager em = EntityManagerSingleton.getEntityManager();
        areaService.updateArea(areas, em);
        close();
    }

    public ArrayList<AreaDomain> getAreas() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        ArrayList<AreaDomain> areaDomains = areaService.getAreas(em);
        close();
        return areaDomains;
    }

    public void updateGame(GameDomain gameDomain) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        gameService.updateGame(gameDomain, em);
        close();
    }

    public GameDomain getGame() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        GameDomain gameDomain = gameService.getGame(em);
        close();
        return gameDomain;
    }

    public void updatePlayer(PlayerDomain p) {
        PlayerDaoImpl pd= new PlayerDaoImpl();
        EntityManager em = EntityManagerSingleton.getEntityManager();
        pd.updatePlayer(p,em);
        close();
    }
}
