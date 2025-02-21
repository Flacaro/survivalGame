package controller;

import model.domain.GameDomain;
import model.domain.MapDomain;
import model.domain.ModeDomain;
import model.domain.PlayerDomain;
import services.MapServices;

public class StartController {

    private final DBController dbController = new DBController();

    public GameDomain start() {
        ModeDomain modeDomain = new ModeDomain(1L);
        PlayerDomain playerDomain = new PlayerDomain("crivall", 5, 1);


        MapDomain mapDomain = new MapDomain();
        MapServices mapServices=new MapServices();
        mapDomain.setAreas(mapServices.setTotalMapArea(modeDomain));

        GameDomain gameDomain = new GameDomain(
                1,
                modeDomain,
                playerDomain,
                mapDomain
        );

        dbController.insertGame(gameDomain);

        return gameDomain;
    }
}
