package controller;

import model.GameFactorySingleton;
import model.domain.*;
import persistence.ResourceDaoImpl;
import services.InventoryService;
import services.MapServices;

import java.util.ArrayList;
import java.util.List;

public class StartController {

    private final DBController dbController = new DBController();

    private GameFactorySingleton gms = GameFactorySingleton.getInstance();

    public GameDomain start() {

        ModeDomain modeDomain = new ModeDomain(1L);
        PlayerDomain playerDomain = new PlayerDomain("crivall", 5, 1);
        InventoryDomain id = new InventoryDomain();
        playerDomain.setInventory(id);

        MapDomain mapDomain = new MapDomain();
        MapServices mapServices = new MapServices();
        mapDomain.setAreas(mapServices.setTotalMapArea(modeDomain));

        GameDomain gameDomain = new GameDomain(
                1,
                modeDomain,
                playerDomain,
                mapDomain
        );
        dbController.insertGame(gameDomain);
        //dobbiamo riprendere le aree dal db altimenti non sono salvate

        ArrayList<AreaDomain> areaDomains = dbController.getAreas();
        gms.createEvent(areaDomains, modeDomain);
        dbController.updateArea(areaDomains);
        mapDomain.setAreas(areaDomains);
        GameDomain gameDB = dbController.getGame();
        dbController.updateGame(gameDB);
        //add resource necessarie per il craftig nell'inventario
        InventoryDomain inventory=dbController.showInventory(playerDomain);
        InventoryService inventoryService=new InventoryService();
        List<ResourceDomain> res= new ArrayList<>();
        ResourceDaoImpl resourceDao= new ResourceDaoImpl();
        res=resourceDao.getResourceByName();
        inventory.setResources(res);
        for(ResourceDomain r:res){
            inventoryService.updateInventory(r,inventory);
        }
        return dbController.getGame();
    }
}
