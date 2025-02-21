package model.domain;


import model.entity.Attack;
import model.entity.Mode;

import java.util.List;

public class Resource {

    private long id;

    private String category;

    private List<Attack> attacks;

    private int level = 1;

    private String name;

    private int quantity;

    private String type;


    public Resource() {
    }

    public Resource(String category, List<Attack> attacks, int level, String name, int quantity, String type) {
        this.category = category;
        this.attacks = attacks;
        this.level = level;
        this.name = name;
        this.quantity = quantity;
        this.type = type;
    }

    public long getId() {
        return id;
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void updateQuantity(String name, int qnt) {
    }

    public void setUp(String type, Mode mode) {
    }
}
