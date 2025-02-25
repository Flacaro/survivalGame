package persistence;

import jakarta.persistence.EntityManager;
import model.domain.ResourceDomain;
import model.entity.Resource;

import java.util.ArrayList;

public interface ResourceDao {
    ArrayList<ResourceDomain> getResources();
    void saveResource(ResourceDomain resource, EntityManager em);
    ResourceDomain getResourceById(long id);
}
