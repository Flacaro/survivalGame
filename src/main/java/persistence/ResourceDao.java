package persistence;

import jakarta.persistence.EntityManager;
import model.domain.ResourceDomain;
import model.entity.Resource;

import java.util.ArrayList;

public interface ResourceDao {
    public ArrayList<ResourceDomain> getResource();
    public void saveResource(ResourceDomain resource, EntityManager em);
}
