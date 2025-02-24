package controller;

import jakarta.persistence.EntityManager;
import model.domain.AreaDomain;
import model.domain.GameDomain;
import persistence.*;
import services.AreaService;
import services.GameService;

import java.util.ArrayList;
import java.util.List;


public class DBController {

    private final GameDaoImpl gameDaoImpl = new GameDaoImpl();
    private final AreaDaoImpl areaDao= new AreaDaoImpl();

    public void insertGame(GameDomain g) {
        GameService gameService=new GameService();
        EntityManager em = EntityManagerSingleton.getEntityManager();

        gameDaoImpl.saveGame(g, em);
        close();
    }

    public void close() {
        EntityManagerSingleton.closeEntityManager();
    }

    public void updateArea(List<AreaDomain> areas) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        areaDao.updateArea(areas,em);
        close();
    }

    public ArrayList<AreaDomain> getAreas() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        ArrayList<AreaDomain> areaDomains= areaDao.getArea(em);
        close();
        return areaDomains;
    }
}
