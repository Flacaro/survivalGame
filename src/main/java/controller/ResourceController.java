package controller;

import model.domain.AreaDomain;
import model.domain.MapDomain;
import model.domain.ResourceDomain;
import services.ResourceService;

public class ResourceController {

    private ResourceService resourceService = new ResourceService();

    //prendere l'idArea del giocatore
    //cercare in quell'area se c'è una risorsa
    //chiedere al giocatore se vuole prendere la risorsa o no
    //nel caso la prende controlla la capienza dell'inventario
    //se ha spazio, aggiungerlo all'inventario altrimenti ERROR

    public void pickUp(long idArea, MapDomain map) {

    }

    public void showResource(ResourceDomain resourceDomain) {
        System.out.println(resourceDomain);
    }
}
