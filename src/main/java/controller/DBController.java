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

    private final AreaService areaService= new AreaService();
    private GameService gameService=new GameService();


    public void insertGame(GameDomain g) {

        EntityManager em = EntityManagerSingleton.getEntityManager();

        gameService.saveGame(g, em);
        close();
    }

    public void close() {
        EntityManagerSingleton.closeEntityManager();
    }

    public void updateArea(List<AreaDomain> areas) {
        AreaService areaService= new AreaService();
        EntityManager em = EntityManagerSingleton.getEntityManager();
        areaService.updateArea(areas,em);
        close();
    }

    public ArrayList<AreaDomain> getAreas() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        ArrayList<AreaDomain> areaDomains= areaService.getAreas(em);
        close();
        return areaDomains;
    }

    public GameService getGameService() {
        return gameService;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }
}
