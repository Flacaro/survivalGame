package model;

import controller.DBController;
import model.entity.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameFactorySingleton {
    private static GameFactorySingleton instance;

    public static GameFactorySingleton getInstance() {
        if (instance == null) {
            instance = new GameFactorySingleton();
        }
        return instance;
    }

    public void createEvent(List<Area> totalMapAreaDomain, Mode modeDomain) {
        int numRes = modeDomain.getNumResources();
        int numEnemy = modeDomain.getNumEnemy();
        DBController dbController = new DBController();

        //devo recuperare le risorse nel db, posizionarle nelle caselle ed aggiornare la quantità
        ArrayList<SimpleResource> resources = dbController.getResources();
        ArrayList<Enemy> enemies = dbController.getEnemies();

        Collections.shuffle(resources);
        Collections.shuffle(enemies);
        //id delle caselle contenenti le risorse
        // 0 index per 4 aree
        List<Area> subListAreaDomain = totalMapAreaDomain.subList(0, numRes + numEnemy);
        //List<Area> subListAreaEnemy = totalMapAreaDomain.subList(0, numEnemy);
        List<SimpleResource> subListResources = resources.subList(0, numRes);
        List<Enemy> subListEnemies = enemies.subList(0, numEnemy);

        for (int j = 0; j < numEnemy; j++) {
            subListAreaDomain.get(j).setIdEvent(subListEnemies.get(j));
            subListAreaDomain.get(j).setCategory(subListEnemies.get(j).getType());
        }

        int resourceIndex = 0;
        for (int i = numEnemy; i < subListAreaDomain.size(); i++) {
            subListAreaDomain.get(i).setIdEvent(subListResources.get(resourceIndex));
            subListAreaDomain.get(i).setCategory(subListResources.get(resourceIndex).getType());
            resourceIndex++;
        }

    }


    public void createClimate(Mode modeDomain) {
        // TODO - implement GameFactorySingleton.createClimate
        throw new UnsupportedOperationException();
    }

    public void setInventory() {
        Inventory inventory = new Inventory();
        throw new UnsupportedOperationException();
    }

}