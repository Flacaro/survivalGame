package controller;

import jakarta.persistence.EntityManager;
import model.entity.*;
import persistence.*;
import java.util.ArrayList;
import java.util.List;


public class DBController {


    GameDaoImpl gameDaoImpl= new GameDaoImpl();
    AreaDaoImpl areaDaoImpl= new AreaDaoImpl();
    PlayerDaoImpl playerDaoImpl= new PlayerDaoImpl();
    MapDaoImpl mapDaoImpl=new MapDaoImpl();
    ResourceDaoImpl resourceDao = new ResourceDaoImpl();
    InventoryDaoImpl inventoryDao = new InventoryDaoImpl();

    public void close() {
        EntityManagerSingleton.closeEntityManager();
    }

    public void insertGame(Game g) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        gameDaoImpl.saveGame(g, em);
        close();
    }


    public void updateArea(List<Area> areas) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        areaDaoImpl.updateArea(areas, em);
        close();
    }

    public ArrayList<Area> getAreas() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        ArrayList<Area> areaDomains = areaDaoImpl.getAreas(em);
        close();
        return areaDomains;
    }

    public void updateGame(Game gameDomain) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        gameDaoImpl.updateGame(gameDomain,em);
        close();
    }

    public Game getGame() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Game gameDomain = gameDaoImpl.getGame(em);
        close();
        return gameDomain;
    }

    public void updatePlayer(Player p) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        playerDaoImpl.updatePlayer(p, em);
        close();
    }


    public Inventory showInventory(Player pd) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Inventory inventoryDomain = playerDaoImpl.getInventoryToShow(pd, em);
        close();
        return inventoryDomain;
    }

    public boolean move(int move, Game game) {
        return game.move(move);
    }

    public ArrayList<CraftedResource> getCraftedResources() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        ResourceDaoImpl resourceDao = new ResourceDaoImpl();
        ArrayList<CraftedResource> getCraftedResources = resourceDao.getResourcesCrafted(em);
        close();
        return getCraftedResources;
    }


    public Area getAreasById(long idArea) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Area areaDomains = areaDaoImpl.getAreaById(em,idArea);
        close();
        return areaDomains;
    }

    public void removeQuantity(ResourceQuantityInv resourceQuantityInvDomain) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        resourceDao.removeQuantity(resourceQuantityInvDomain, em);
        close();
    }

    public void updateMap(Map map, Resource resourceDomain) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        mapDaoImpl.updateMap(map, resourceDomain,em);
        close();
    }


    public ArrayList<Resource> getResources() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        ArrayList<Resource> resources=resourceDao.getResources(em);
        close();
        return resources;
    }

    public Resource getResourceById(long id) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Resource resource=resourceDao.getResourceById(id,em);
        close();
        return resource;
    }

    public List<Resource> getResourcesByName() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        List<Resource> resources=resourceDao.getResourceByName(em);
        close();
        return resources;
    }


    public boolean updateInventory(Resource res, Inventory id) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        boolean updated=inventoryDao.updateInventory(res, id,em);
        close();
        return updated ;
    }

    public Inventory updateInventoryCraft(Inventory id) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Inventory inventory=inventoryDao.updateInventoryCraft(id,em);
        close();
        return inventory;
    }

    public Mode getModeById(int choice) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        ModeDaoImpl modeDao=new ModeDaoImpl();
        Mode mode = modeDao.getModeById(choice,em);
        close();
        return mode;
    }
}
