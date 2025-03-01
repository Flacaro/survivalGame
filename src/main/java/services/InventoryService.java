package services;

import jakarta.persistence.EntityManager;
import model.domain.AttackDomain;
import model.domain.InventoryDomain;
import model.domain.ResourceDomain;
import model.entity.Attack;
import model.entity.Inventory;
import model.entity.Resource;
import persistence.EntityManagerSingleton;
import persistence.InventoryDaoImpl;

import java.util.ArrayList;
import java.util.List;

public class InventoryService {

    private ResourceService rs;

    public Inventory inventoryMapper(InventoryDomain id) {
        Inventory i = new Inventory();
        ResourceService resourceService=new ResourceService();
        i.setId(id.getId());
        i.setCapacity(id.getCapacity());
        List<Resource> list= new ArrayList<>();
        if(id.getResources()!=null){
        List<ResourceDomain> domainList= id.getResources();
        for (ResourceDomain a :domainList){

            list.add(resourceService.resourceMapper(a));
        }
        i.setResources(list);}

        List<Resource> listS= new ArrayList<>();
        if(id.getResourcesSelected()!=null){
        List<ResourceDomain> domainListS= id.getResourcesSelected();
        for (ResourceDomain a :domainListS){

            list.add(resourceService.resourceMapper(a));
        }
        i.setResourcesSelected(listS);}

        return i;
    }

    public InventoryDomain inventoryDomainMapper(Inventory i) {
        InventoryDomain id = new InventoryDomain();
        ResourceService resourceService=new ResourceService();
        id.setId(i.getId());
        id.setCapacity(i.getCapacity());
        if(i.getResources()!=null){
        List<Resource> list= i.getResources();
        List<ResourceDomain> domainList=new ArrayList<>() ;
        for (Resource r :list){

            domainList.add(resourceService.resourceDomainMapper(r));
        }
        id.setResources(domainList);}
        List<ResourceDomain> domainListS= new ArrayList<>();
        if(i.getResourcesSelected()!=null){
        List<Resource> listS=i.getResourcesSelected();

        for (Resource a :listS){

            domainListS.add(resourceService.resourceDomainMapper(a));
        }}
        id.setResourcesSelected(domainListS);
        return id;
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

    public boolean checkCapacity(InventoryDomain inventoryDomain) {
        return inventoryDomain.getCapacity() > 0;
    }

    public void addSelections(Resource res) {
        // TODO - implement Inventory.addSelections
        throw new UnsupportedOperationException();
    }

    public void useResource(Resource resource) {
        // TODO - implement Inventory.useResource
        throw new UnsupportedOperationException();
    }

    public void updateInventory(InventoryDomain id) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        InventoryDaoImpl inventoryDao = new InventoryDaoImpl();
        inventoryDao.updateInventory(id, em);
    }
}
