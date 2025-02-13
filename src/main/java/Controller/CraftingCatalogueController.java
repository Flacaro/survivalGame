package Controller;

import org.example.model.CraftingCatalog;
import org.example.persistence.CraftingCatalogDaoImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CraftingCatalogueController {

    private CraftingCatalog craftingCatalog;
    private CraftingCatalogDaoImpl craftingCatalogDaoImpl;

    public Long craftingResources(ArrayList<Long> selections) {
        Long idFinalResource = 0L;
        List<CraftingCatalog> resourcesToCraft = craftingCatalogDaoImpl.getResourcesIdsToCraft();

        for (CraftingCatalog catalog : resourcesToCraft) {
            List<Long> dbIds = parseResourceIds(catalog.getResourcesIdsToCraft().toString());
            if (craftingCatalog.checkCompatibility(selections, (ArrayList<Long>) dbIds)) {
                idFinalResource = catalog.getFinalResource();
            }
        }
        return idFinalResource;
        }

    // Converte una stringa "1,2,3" in una lista di Long
    private List<Long> parseResourceIds(String resources) {
        if (resources == null || resources.isEmpty()) return Collections.emptyList();
        String[] ids = resources.split(",");
        List<Long> result = new ArrayList<>();
        for (String id : ids) {
            result.add(Long.parseLong(id.trim()));
        }
        return result;
    }
}
