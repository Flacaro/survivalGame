package services;

import model.domain.CraftedResourceDomain;
import model.domain.InventoryDomain;
import model.domain.ResourceDomain;
import model.entity.CraftedResource;
import model.entity.Inventory;
import model.entity.Resource;
import persistence.InventoryDaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InventoryService {

    private ResourceService rs;

    public Inventory inventoryMapper(InventoryDomain id) {
        Inventory i = new Inventory();
        ResourceService resourceService = new ResourceService();
        i.setId(id.getId());
        i.setCapacity(id.getCapacity());
        List<Resource> list = new ArrayList<>();
        if (id.getResources() != null) {
            List<ResourceDomain> domainList = id.getResources();
            for (ResourceDomain a : domainList) {

                list.add(resourceService.resourceMapper(a));
            }
            i.setResources(list);
        }

        CraftedResourceService cs=new CraftedResourceService();
        List<CraftedResource> listS = new ArrayList<>();
        if (id.getResourcesSelected() != null) {
            List<CraftedResourceDomain> domainListS = id.getResourcesSelected();
            for (CraftedResourceDomain a : domainListS) {
                listS.add(cs.craftedResourceMapper(a));
            }
            i.setResourcesSelected(listS);
        }

        return i;
    }

    public InventoryDomain inventoryDomainMapper(Inventory i) {
        InventoryDomain id = new InventoryDomain();
        ResourceService resourceService = new ResourceService();
        id.setId(i.getId());
        id.setCapacity(i.getCapacity());
        if (i.getResources() != null) {
            List<Resource> list = i.getResources();
            List<ResourceDomain> domainList = new ArrayList<>();
            for (Resource r : list) {

                domainList.add(resourceService.resourceDomainMapper(r));
            }
            id.setResources(domainList);
        }
        CraftedResourceService cs=new CraftedResourceService();
        List<CraftedResourceDomain> domainListS = new ArrayList<>();
        if (i.getResourcesSelected() != null) {
            List<CraftedResource> listS = i.getResourcesSelected();

            for (CraftedResource a : listS) {
                domainListS.add(cs.craftedResourceDomainMapper(a));
            }
        }
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

    public InventoryDomain remove(ArrayList<ResourceDomain> selections, InventoryDomain inventoryDomain) {
        for (ResourceDomain r:selections){
            for(ResourceDomain res :inventoryDomain.getResources()){
                if (Objects.equals(r.getName(), res.getName())){
                    if (res.getQuantity()!=0){
                        res.setQuantity(res.getQuantity()-1);
                    }else {
                        inventoryDomain.getResources().remove(res);
                    }
                }
            }
        }
        return inventoryDomain;
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

    public boolean updateInventory(ResourceDomain res, InventoryDomain id) {
        InventoryDaoImpl inventoryDao = new InventoryDaoImpl();
        return inventoryDao.updateInventory(res, id);
    }
    public void updateInventoryCraft(CraftedResourceDomain res, InventoryDomain id, List<ResourceDomain> list) {
        InventoryDaoImpl inventoryDao = new InventoryDaoImpl();
        inventoryDao.updateInventoryCraft(res,list, id);
    }
}
