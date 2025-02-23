package persistence;

import model.domain.CraftingCatalogDomain;
import model.entity.CraftingCatalog;

import java.util.ArrayList;

public interface CraftingCatalogDao {

    ArrayList<CraftingCatalogDomain> getResourcesToCraft();
}
