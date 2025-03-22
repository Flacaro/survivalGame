package services;

import controller.DBController;
import model.domain.CraftedResourceDomain;
import model.domain.InventoryDomain;
import model.domain.ResourceDomain;
import model.domain.ResourceQuantityInvDomain;
import model.entity.CraftedResource;
import model.entity.Inventory;
import model.entity.Resource;
import model.entity.ResourceQuantityInv;
import persistence.InventoryDaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InventoryService {

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

        CraftedResourceService cs = new CraftedResourceService();
        List<CraftedResource> listS = new ArrayList<>();
        if (id.getCraftedResourceDomainList() != null) {
            List<CraftedResourceDomain> domainListS = id.getCraftedResourceDomainList();
            for (CraftedResourceDomain a : domainListS) {
                listS.add(cs.craftedResourceMapper(a));
            }
            i.setCraftedResourceList(listS);
        }
        ResourceQuantityInvService resourceQuantityInvService = new ResourceQuantityInvService();
        List<ResourceQuantityInv> res_quant = new ArrayList<>();
        for (ResourceQuantityInvDomain r : id.getResources_quantity()) {
            res_quant.add(resourceQuantityInvService.resourceQuantityInvMapper(r));
        }
        i.setResources_quantity(res_quant);
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
        CraftedResourceService cs = new CraftedResourceService();
        List<CraftedResourceDomain> domainListS = new ArrayList<>();
        if (i.getCraftedResourceList() != null) {
            List<CraftedResource> listS = i.getCraftedResourceList();

            for (CraftedResource a : listS) {
                domainListS.add(cs.craftedResourceDomainMapper(a));
            }
        }
        id.setCraftedResourceDomainList(domainListS);
        ResourceQuantityInvService resourceQuantityInvService = new ResourceQuantityInvService();
        List<ResourceQuantityInvDomain> res_quant = new ArrayList<>();
        for (ResourceQuantityInv r : i.getResources_quantity()) {
            res_quant.add(resourceQuantityInvService.resourceQuantityInvDomainMapper(r));
        }
        id.setResources_quantity(res_quant);
        return id;
    }

    public InventoryDomain remove(ArrayList<ResourceDomain> selections, InventoryDomain inventoryDomain) {
        List<ResourceQuantityInvDomain> quantity=inventoryDomain.getResources_quantity();
        ArrayList<ResourceQuantityInvDomain> rqiToRemove = new ArrayList<>();
        ArrayList<ResourceDomain> toRemove = new ArrayList<>();
        for (ResourceQuantityInvDomain q : quantity){
            for (ResourceDomain r :selections){
                if (q.getResource().getId()== r.getId()){
                    q.setQuantity(q.getQuantity()-1);
                    if (q.getQuantity()==0){
                        inventoryDomain.setCapacity(inventoryDomain.getCapacity()+1);
                        rqiToRemove.add(q);
                        toRemove.add(r);
                    }
                    break;
                }
            }
        }
        inventoryDomain.getResources().removeAll(toRemove);
        quantity.removeAll(rqiToRemove);
        inventoryDomain.setResources_quantity(quantity);
        return inventoryDomain;
    }

    public boolean checkCapacity(InventoryDomain inventoryDomain) {
        return inventoryDomain.getCapacity() > 0;
    }

    public boolean updateInventory(ResourceDomain res, InventoryDomain id) {
        InventoryDaoImpl inventoryDao = new InventoryDaoImpl();
        return inventoryDao.updateInventory(res, id);
    }

    public InventoryDomain updateInventoryCraft(InventoryDomain id) {
        InventoryDaoImpl inventoryDao = new InventoryDaoImpl();
        return inventoryDao.updateInventoryCraft(id);
    }
}
