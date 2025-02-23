package model.domain;
import model.entity.Attack;
import model.entity.Mode;

import java.util.ArrayList;

public class EnemyDomain {

    private Long id;

    private ArrayList<Attack> attacks= new ArrayList<>();

    private int level = 1;

    private String name;

    private String description;

    private String type;

    public EnemyDomain() {
    }

    public EnemyDomain(ArrayList<Attack> attacks, int level, String name) {
        this.attacks = attacks;
        this.level = level;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(ArrayList<Attack> attacks) {
        this.attacks = attacks;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void updateQuantity(String name, int qnt) {
    }

    public void setUp(String type, Mode mode) {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
