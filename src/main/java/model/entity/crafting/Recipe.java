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
    @Column(name = "INGREDIENT")
    private List<Ingredient> ingredient = new ArrayList<>();
}
