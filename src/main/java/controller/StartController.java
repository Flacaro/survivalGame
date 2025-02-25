package controller;

import model.GameFactorySingleton;
import model.domain.*;
import services.MapServices;

import java.util.ArrayList;

public class StartController {

    private final DBController dbController = new DBController();

    private GameFactorySingleton gms = GameFactorySingleton.getInstance();

    public GameDomain start() {

        ModeDomain modeDomain = new ModeDomain(1L);
        PlayerDomain playerDomain = new PlayerDomain("crivall", 5, 1);


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
        gms.createEvent(mapDomain.getAreas(), modeDomain);
        dbController.updateArea(mapDomain.getAreas());
        return gameDomain;
    }
}
