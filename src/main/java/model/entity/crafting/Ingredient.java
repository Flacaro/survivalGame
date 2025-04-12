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
    @JoinColumn(name = "RESOURCE")
    private SimpleResource resource;

    private int quantity;

    @ManyToOne
    @JoinColumn(name="RECIPE")
    private Recipe recipe;
}
