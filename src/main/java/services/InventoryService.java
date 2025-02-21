package services;

import model.domain.AttackDomain;
import model.domain.InventoryDomain;
import model.domain.ResourceDomain;
import model.entity.Attack;
import model.entity.Inventory;
import model.entity.Resource;

import java.util.ArrayList;
import java.util.List;

public class InventoryService {

    private ResourceService rs;

    public Inventory inventoryMapper(InventoryDomain id) {
        Inventory i = new Inventory();
        ResourceService resourceService=new ResourceService();
        i.setCapacity(id.getCapacity());
        List<Resource> list= new ArrayList<>();
        List<ResourceDomain> domainList= id.getResources();
        for (ResourceDomain a :domainList){
            list.add(resourceService.resourceMapper(a));
        }
        i.setResources(list);

        List<Resource> listS= new ArrayList<>();
        List<ResourceDomain> domainListS= id.getResources();
        for (ResourceDomain a :domainListS){
            list.add(resourceService.resourceMapper(a));
        }
        i.setResourcesSelected(listS);

        return i;
    }

    public Resource combine(Resource[] selections) {
        // TODO - implement Inventory.combine
        throw new UnsupportedOperationException();
    }

    public boolean add(Resource res) {
        // TODO - implement Inventory.add
        throw new UnsupportedOperationException();
    }


    public boolean remove(Resource[] selections, int[] qnt) {
        // TODO - implement Inventory.remove
        throw new UnsupportedOperationException();
    }

    public boolean checkCapacity() {
        // TODO - implement Inventory.checkCapacity
        throw new UnsupportedOperationException();
    }

    public void addSelections(Resource res) {
        // TODO - implement Inventory.addSelections
        throw new UnsupportedOperationException();
    }

    public void useResource(Resource resource) {
        // TODO - implement Inventory.useResource
        throw new UnsupportedOperationException();
    }
}
