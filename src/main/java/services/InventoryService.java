package services;

import model.domain.CraftedResourceDomain;
import model.domain.InventoryDomain;
import model.domain.ResourceDomain;
import model.domain.ResourceQuantityInvDomain;
import model.entity.CraftedResource;
import model.entity.Inventory;
import model.entity.Resource;
import model.entity.ResourceQuantityInv;
import persistence.InventoryDaoImpl;

import java.util.*;

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

        CraftedResourceService cs=new CraftedResourceService();
        List<CraftedResource> listS = new ArrayList<>();
        if (id.getCraftedResourceDomainList() != null) {
            List<CraftedResourceDomain> domainListS = id.getCraftedResourceDomainList();
            for (CraftedResourceDomain a : domainListS) {
                listS.add(cs.craftedResourceMapper(a));
            }
            i.setCraftedResourceList(listS);
        }
        ResourceQuantityInvService resourceQuantityInvService=new ResourceQuantityInvService();
        List<ResourceQuantityInv> res_quant = new ArrayList<>();
        for(ResourceQuantityInvDomain r :id.getResources_quantity()){
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
        CraftedResourceService cs=new CraftedResourceService();
        List<CraftedResourceDomain> domainListS = new ArrayList<>();
        if (i.getCraftedResourceList() != null) {
            List<CraftedResource> listS = i.getCraftedResourceList();

            for (CraftedResource a : listS) {
                domainListS.add(cs.craftedResourceDomainMapper(a));
            }
        }
        id.setCraftedResourceDomainList(domainListS);
        ResourceQuantityInvService resourceQuantityInvService=new ResourceQuantityInvService();
        List<ResourceQuantityInvDomain> res_quant = new ArrayList<>();
        for(ResourceQuantityInv r :i.getResources_quantity()){
            res_quant.add(resourceQuantityInvService.resourceQuantityInvDomainMapper(r));
        }
        id.setResources_quantity(res_quant);
        return id;
    }

    public InventoryDomain remove(ArrayList<ResourceDomain> selections, InventoryDomain inventoryDomain) {
        ArrayList<ResourceDomain> toRemove=new ArrayList<>();
        ArrayList<ResourceQuantityInvDomain> rqiToRemove= new ArrayList<>();
        List<ResourceQuantityInvDomain> resourcesQuantity = inventoryDomain.getResources_quantity();
        ResourceQuantityInvService resourceQuantityInvService= new ResourceQuantityInvService();
        for (ResourceDomain r:selections){
            for(ResourceDomain res :inventoryDomain.getResources()){
                for (ResourceQuantityInvDomain rqi : resourcesQuantity){
                    if (Objects.equals(r.getName(), res.getName())&& Objects.equals(r.getName(),rqi.getResource().getName())){
                        if (rqi.getQuantity()!=0){
                        rqi.setQuantity(rqi.getQuantity()-1);
                        //res.setQuantity(res.getQuantity()-1);
                            if (rqi.getQuantity()==0){
                                rqiToRemove.add(rqi);
                                toRemove.add(res);
//                            resourcesQuantity.remove(rqi);
                            //resourceQuantityInvService.remove(rqi);
                            }
                        }else {
                        toRemove.add(res);
                        rqiToRemove.add(rqi);
//                        resourceQuantityInvService.remove(rqi);
//                        resourcesQuantity.remove(rqi);
                        }
                    }
                }
            }
       }
        for (ResourceQuantityInvDomain r: rqiToRemove){
          resourceQuantityInvService.remove(r);
        }
//        inventoryDomain.getResources_quantity().removeAll(rqiToRemove);
        inventoryDomain.getResources().removeAll(toRemove);
//        for (ResourceQuantityInvDomain r: rqiToRemove){
//            resourceQuantityInvService.remove(r);
//        }
        return inventoryDomain;
    }

    public boolean checkCapacity(InventoryDomain inventoryDomain) {
        return inventoryDomain.getCapacity() > 0;
    }

    public boolean updateInventory(ResourceDomain res, InventoryDomain id) {
        InventoryDaoImpl inventoryDao = new InventoryDaoImpl();
        return inventoryDao.updateInventory(res, id);
    }
    public void updateInventoryCraft(InventoryDomain id) {
        InventoryDaoImpl inventoryDao = new InventoryDaoImpl();
        inventoryDao.updateInventoryCraft(id);
    }
}
