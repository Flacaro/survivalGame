package controller;

import jakarta.persistence.EntityManager;
import model.domain.*;
import persistence.*;
import services.AreaService;
import services.GameService;
import services.PlayerService;

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
        pd.updatePlayer(p);
        close();
    }


    public InventoryDomain showInventory(PlayerDomain pd) {
        PlayerDaoImpl pdao= new PlayerDaoImpl();
        EntityManager em = EntityManagerSingleton.getEntityManager();
        InventoryDomain inventoryDomain=pdao.getInventorytoShow(pd);
        close();
        return inventoryDomain;
    }

    public boolean move(int move, GameDomain game){
        PlayerService playerServices= new PlayerService();
        return playerServices.move(move,game);
    }

    public ArrayList<CraftedResourceDomain> getCraftedResources() {
        ResourceDaoImpl resourceDao= new ResourceDaoImpl();
        EntityManager em = EntityManagerSingleton.getEntityManager();
        ArrayList<CraftedResourceDomain> getCraftedResources= resourceDao.getResourcesCrafted(em);
        close();
        return  getCraftedResources;
    }

    public void removeResources(ArrayList<ResourceDomain> listResSel, InventoryDomain inventoryDomain) {
        ResourceDaoImpl resourceDao= new ResourceDaoImpl();
        EntityManager em = EntityManagerSingleton.getEntityManager();
        resourceDao.removeResources(inventoryDomain,em);
        close();
    }

    public AreaDomain getAreasById(long idArea) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        AreaDomain areaDomains = areaService.getAreaById(em,idArea);
        close();
        return areaDomains;
    }
}
