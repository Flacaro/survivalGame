package controller;

import model.domain.*;
import services.ResourceService;

public class ResourceController {

    private ResourceService resourceService = new ResourceService();

    public static InventoryDomain showInventory(PlayerDomain pd) {
        DBController dbController=new DBController();
        return dbController.getInventorytoShow(pd);
    }


    public void showResource(ResourceDomain resourceDomain) {
        System.out.println(resourceDomain);
    }
}
