package model.entity.crafting;

import jakarta.persistence.*;
import model.entity.SimpleResource;

@Entity
@Table(name = "INGREDIENT")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "RESOURCE_ID")
    private SimpleResource resource;

    private int quantity;

    @ManyToOne
    @JoinColumn(name="RECIPE_ID")
    private Recipe recipe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SimpleResource getResource() {
        return resource;
    }

    public void setResource(SimpleResource resource) {
        this.resource = resource;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
