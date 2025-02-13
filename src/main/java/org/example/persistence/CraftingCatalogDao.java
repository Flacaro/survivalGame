package org.example.persistence;

import org.example.model.CraftingCatalog;
import java.util.List;

public interface CraftingCatalogDao {

    List<CraftingCatalog> getResourcesIdsToCraft();
}
