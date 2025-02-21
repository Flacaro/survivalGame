package model.domain;

public class AttackDomain {

    private long id;

    private String name;

    private double damage;

    private String type;


    public AttackDomain() {
    }

    public AttackDomain(String name, double damage, String type) {
        this.name = name;
        this.damage = damage;
        this.type = type;
    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
