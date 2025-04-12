package model.entity.crafting;

import jakarta.persistence.*;
import model.entity.CraftedResource;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RECIPE")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private CraftedResource craftedResource;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> ingredient = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CraftedResource getCraftedResource() {
        return craftedResource;
    }

    public void setCraftedResource(CraftedResource craftedResource) {
        this.craftedResource = craftedResource;
    }

    public List<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }
}
