package model.domain;


import java.util.List;
import java.util.Objects;

public class ResourceDomain {

    private long id;

    private String category;

    private List<AttackDomain> attacks;

    private int level = 1;

    private String name;

    private int quantity;

    private String type;


    public ResourceDomain() {
    }

    public ResourceDomain(String category, List<AttackDomain> attacks, int level, String name, int quantity, String type) {
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

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<AttackDomain> getAttacks() {
        return attacks;
    }

    public void setAttacks(List<AttackDomain> attacks) {
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

    public void setUp(String type, ModeDomain mode) {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ResourceDomain that = (ResourceDomain) o;
        return id == that.id && level == that.level && quantity == that.quantity && Objects.equals(category, that.category) && Objects.equals(attacks, that.attacks) && Objects.equals(name, that.name) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, attacks, level, name, quantity, type);
    }

    @Override
    public String toString() {
        return "ResourceDomain{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", attacks=" + attacks +
                ", level=" + level +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", type='" + type + '\'' +
                '}';
    }
}
