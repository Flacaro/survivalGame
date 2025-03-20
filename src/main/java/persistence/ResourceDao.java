package persistence;

import jakarta.persistence.EntityManager;
import model.domain.CraftedResourceDomain;
import model.domain.InventoryDomain;
import model.domain.ResourceDomain;
import model.domain.ResourceQuantityInvDomain;

import java.util.ArrayList;
import java.util.List;

public interface ResourceDao {

    ArrayList<ResourceDomain> getResources();

    void saveResource(ResourceDomain resource);

    ResourceDomain getResourceById(long id);

    ArrayList<CraftedResourceDomain> getResourcesCrafted(EntityManager em);

    List<ResourceDomain> getResourceByName();

    void removeResources(InventoryDomain inventoryDomain, EntityManager em);

    void removeQuantity(ResourceQuantityInvDomain resourceQuantityInvDomain,EntityManager em);
}
