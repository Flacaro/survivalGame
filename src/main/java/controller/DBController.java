package controller;

import jakarta.persistence.EntityManager;
import model.domain.GameDomain;
import persistence.*;
import services.GameService;


public class DBController {

    private final GameDaoImpl gameDaoImpl = new GameDaoImpl();

    public void insertGame(GameDomain g) {
        GameService gameService=new GameService();
        EntityManager em = EntityManagerSingleton.getEntityManager();
        gameService.saveGame(g, em);
        close();
    }

    public void close() {
        EntityManagerSingleton.closeEntityManager();
    }
}
