package controller;

import jakarta.persistence.EntityManager;
import model.entity.*;
import persistence.*;

import java.util.ArrayList;
import java.util.List;


public class DBController {


    GameDaoImpl gameDaoImpl = new GameDaoImpl();
    AreaDaoImpl areaDaoImpl = new AreaDaoImpl();
    PlayerDaoImpl playerDaoImpl = new PlayerDaoImpl();
    MapDaoImpl mapDaoImpl = new MapDaoImpl();
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
        ArrayList<Area> areas = areaDaoImpl.getAreas(em);
        close();
        return areas;
    }

    public void updateGame(Game gameDomain) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        gameDaoImpl.updateGame(gameDomain, em);
        close();
    }

    public Game getGame() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Game game = gameDaoImpl.getGame(em);
        close();
        return game;
    }

    public void updatePlayer(Player p) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        playerDaoImpl.updatePlayer(p, em);
        close();
    }


    public Inventory showInventory(Player p) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Inventory inventory = playerDaoImpl.getInventoryToShow(p, em);
        close();
        return inventory;
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
        Area area = areaDaoImpl.getAreaById(em, idArea);
        close();
        return area;
    }

    public void removeQuantity(ResourceQuantityInv resourceQuantityInv) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        resourceDao.removeQuantity(resourceQuantityInv, em);
        close();
    }

    public void updateMap(Map map, SimpleResource resource) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        mapDaoImpl.updateMap(map, resource, em);
        close();
    }


    public ArrayList<SimpleResource> getResources() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        ArrayList<SimpleResource> resources = resourceDao.getResources(em);
        close();
        return resources;
    }

    public SimpleResource getResourceById(long id) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        SimpleResource resource = resourceDao.getResourceById(id, em);
        close();
        return resource;
    }

    public List<SimpleResource> getResourcesByName() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        List<SimpleResource> resources = resourceDao.getResourceByName(em);
        close();
        return resources;
    }


    public boolean updateInventory(SimpleResource res, Inventory id) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        boolean updated = inventoryDao.updateInventory(res, id, em);
        close();
        return updated;
    }

    public Inventory updateInventoryCraft(Inventory id) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Inventory inventory = inventoryDao.updateInventoryCraft(id, em);
        close();
        return inventory;
    }

    public Mode getModeById(int choice) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        ModeDaoImpl modeDao = new ModeDaoImpl();
        Mode mode = modeDao.getModeById(choice, em);
        close();
        return mode;
    }

    public ArrayList<Enemy> getEnemies() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EnemyDaoImpl enemyDao = new EnemyDaoImpl();
        ArrayList<Enemy> enemies = enemyDao.getEnemies(em);
        close();
        return enemies;
    }
}
