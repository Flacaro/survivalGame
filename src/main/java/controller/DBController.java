package controller;

import jakarta.persistence.EntityManager;
import model.domain.GameDomain;
import persistence.*;


public class DBController {

    private final GameDaoImpl gameDaoImpl = new GameDaoImpl();

    public void insertGame(GameDomain g) {
        EntityManager em = EntityManagerSingleton.getEntityManager();

        gameDaoImpl.saveGame(g, em);
        close();
    }

    public void close() {
        EntityManagerSingleton.closeEntityManager();
    }
}
