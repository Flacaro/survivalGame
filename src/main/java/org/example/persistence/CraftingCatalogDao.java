package org.example.persistence;

import org.example.model.CraftingCatalog;

import java.util.ArrayList;

public interface CraftingCatalogDao {

    ArrayList<CraftingCatalog> getResourcesToCraft();
}
