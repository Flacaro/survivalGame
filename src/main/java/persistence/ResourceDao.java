package persistence;

import model.domain.ResourceDomain;
import java.util.ArrayList;

public interface ResourceDao {

    ArrayList<ResourceDomain> getResources();

    void saveResource(ResourceDomain resource);

    ResourceDomain getResourceById(long id);
}
