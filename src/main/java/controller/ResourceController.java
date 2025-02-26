package controller;

import model.domain.AreaDomain;
import model.domain.MapDomain;
import model.domain.ResourceDomain;
import services.ResourceService;

public class ResourceController {

    private ResourceService resourceService = new ResourceService();


    public void showResource(ResourceDomain resourceDomain) {
        System.out.println(resourceDomain);
    }
}
