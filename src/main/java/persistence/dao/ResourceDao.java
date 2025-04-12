package persistence.dao;

import jakarta.persistence.EntityManager;
import model.entity.*;

import java.util.ArrayList;
import java.util.List;

public interface ResourceDao {

    ArrayList<SimpleResource> getResources(EntityManager em);

    void saveResource(SimpleResource resource,EntityManager em);

    Resource getResourceById(long id,EntityManager em);

    ArrayList<CraftedResource> getResourcesCrafted(EntityManager em);

    List<SimpleResource> getResourceByName(EntityManager em);

    void removeResources(Inventory inventoryDomain, EntityManager em);

    void removeQuantity(ResourceQuantityInv resourceQuantityInvDomain, EntityManager em);
}
