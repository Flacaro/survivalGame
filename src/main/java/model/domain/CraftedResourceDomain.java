package model.domain;

import model.entity.Attack;

import java.util.ArrayList;
import java.util.List;

public class CraftedResourceDomain {

    private long id;

    private String name;

    private String description;

    private String category;

    private List<model.entity.Attack> attacks = new ArrayList<>();

    private int level = 1;

    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CraftedResourceDomain() {
    }

    public CraftedResourceDomain(String category, List<model.entity.Attack> attacks, int level, String name, String description) {
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

    public List<model.entity.Attack> getAttacks() {
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
