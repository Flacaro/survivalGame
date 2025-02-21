package model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CRAFTING_CATALOG")
public class CraftingCatalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resource> resourcesToCraft = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private CraftedResource finalResource;

    public long getId() {
        return id;
    }

    public CraftingCatalog() {
    }

    public ArrayList<Resource> getResourcesToCraft() {
        return (ArrayList<Resource>) resourcesToCraft;
    }

    public void setResourcesToCraft(ArrayList<Resource> resourcesToCraft) {
        this.resourcesToCraft = resourcesToCraft;
    }

    public CraftedResource getFinalResource() {
        return finalResource;
    }

    public void setFinalResource(CraftedResource finalResource) {
        this.finalResource = finalResource;
    }

    @Override
    public String toString() {
        return "CraftingCatalog{" +
                "id=" + id +
                ", resourcesToCraft=" + resourcesToCraft +
                ", finalResource=" + finalResource +
                '}';
    }

//    public boolean checkCompatibility(ArrayList<Long> selections, ArrayList<Resource> resourcesToCraft) {
//        ArrayList<Long> resourcesIdsToCraft = new ArrayList<>();
//        for(Resource r : resourcesToCraft) {
//            resourcesIdsToCraft.add(r.getId());
//        }
//        return resourcesIdsToCraft.equals(selections);
//    }

}