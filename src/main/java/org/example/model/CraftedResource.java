package org.example.model;

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

    public CraftedResource() {
    }

    public CraftedResource(String category, List<Attack> attacks, int level, String name, String description) {
        this.category = category;
        this.attacks = attacks;
        this.level = level;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
