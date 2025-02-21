package controller;

import jakarta.persistence.EntityManager;
import model.entity.Game;
import persistence.*;


public class DBController {

    private final GameDaoImpl gameDaoImpl = new GameDaoImpl();

    public void insertGame(Game g) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        gameDaoImpl.saveGame(g, em);
        close();
    }

    public void close() {
        EntityManagerSingleton.closeEntityManager();
    }
}
