package persistence;

import model.entity.CraftingCatalog;

import java.util.ArrayList;

public interface CraftingCatalogDao {

    ArrayList<CraftingCatalog> getResourcesToCraft();
}
