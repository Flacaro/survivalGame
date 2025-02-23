package services;

import model.domain.CraftedResourceDomain;
import model.entity.CraftedResource;

public class CraftedResourceServices {

    public CraftedResource craftedResourceMapper(CraftedResourceDomain craftedResourceDomain){
        CraftedResource craftedResource=new CraftedResource();
        craftedResource.setId(craftedResourceDomain.getId());
        craftedResource.setName(craftedResourceDomain.getName());
        craftedResource.setDescription(craftedResourceDomain.getDescription());
        craftedResource.setCategory(craftedResourceDomain.getCategory());
        craftedResource.setAttacks(craftedResourceDomain.getAttacks());
        craftedResource.setLevel(craftedResourceDomain.getLevel());
        return craftedResource;
    }

    public CraftedResourceDomain craftedResourceDomainMapper(CraftedResource craftedResource){
        CraftedResourceDomain craftedResourceDomain=new CraftedResourceDomain();
        craftedResourceDomain.setId(craftedResource.getId());
        craftedResourceDomain.setName(craftedResource.getName());
        craftedResourceDomain.setDescription(craftedResource.getDescription());
        craftedResourceDomain.setCategory(craftedResource.getCategory());
        craftedResourceDomain.setAttacks(craftedResource.getAttacks());
        craftedResourceDomain.setLevel(craftedResource.getLevel());
        return craftedResourceDomain;
    }
}
