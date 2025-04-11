package persistence;

import jakarta.persistence.EntityManager;
import model.entity.CraftedResource;
import model.entity.Inventory;
import model.entity.Resource;
import model.entity.ResourceQuantityInv;

import java.util.ArrayList;
import java.util.List;

public interface ResourceDao {

    ArrayList<Resource> getResources(EntityManager em);

    void saveResource(Resource resource,EntityManager em);

    Resource getResourceById(long id,EntityManager em);

    ArrayList<CraftedResource> getResourcesCrafted(EntityManager em);

    List<Resource> getResourceByName(EntityManager em);

    void removeResources(Inventory inventoryDomain, EntityManager em);

    void removeQuantity(ResourceQuantityInv resourceQuantityInvDomain, EntityManager em);
}
