package model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CRAFTED_RESOURCE")
public class CraftedResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "CATEGORY", nullable = false)
    private String category;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attack> attacks=new ArrayList<>();

    @Column(name = "LEVEL", nullable = false)
    private int level = 1;

    @Column(name = "QUANTITY")
    private int quantity;
    @Column(name = "TYPE")
    private String type;

    public CraftedResource(long id, String name, String description, String category, List<Attack> attacks, int level, int quantity, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.attacks = attacks;
        this.level = level;
        this.quantity = quantity;
        this.type = type;
    }

    public CraftedResource() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(List<Attack> attacks) {
        this.attacks = attacks;
    }

    public int getLevel() {
        return level;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
