package controller;

import jakarta.persistence.EntityManager;
import model.entity.*;
import persistence.*;
import singleton.EntityManagerSingleton;

import java.util.ArrayList;
import java.util.List;


public class DBController {


    GameDaoImpl gameDaoImpl = new GameDaoImpl();
    AreaDaoImpl areaDaoImpl = new AreaDaoImpl();
    ResourceDaoImpl resourceDao = new ResourceDaoImpl();
    EnemyDaoImpl enemyDao = new EnemyDaoImpl();
    CheckpointDaoImpl ckDaoImpl = new CheckpointDaoImpl();

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

    public ArrayList<CraftedResource> getCraftedResources() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        ResourceDaoImpl resourceDao = new ResourceDaoImpl();
        ArrayList<CraftedResource> getCraftedResources = resourceDao.getResourcesCrafted(em);
        close();
        return getCraftedResources;
    }

    public ArrayList<SimpleResource> getResources() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        ArrayList<SimpleResource> resources = resourceDao.getResources(em);
        close();
        return resources;
    }

    public List<SimpleResource> getResourcesByName() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        List<SimpleResource> resources = resourceDao.getResourceByName(em);
        close();
        return resources;
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
        ArrayList<Enemy> enemies = enemyDao.getEnemies(em);
        close();
        return enemies;
    }

    public List<Checkpoint> getCheckpoints() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        return ckDaoImpl.getCheckpoints(em);
    }


}
