package persistence;

import model.CraftingCatalog;

import java.util.ArrayList;

public interface CraftingCatalogDao {

    ArrayList<CraftingCatalog> getResourcesToCraft();
}
