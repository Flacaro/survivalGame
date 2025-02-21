package model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "ATTACK")
public class Attack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DAMAGE", nullable = false)
    private double damage;

    @Column(name = "TYPE", nullable = false)
    private String type;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Attack attack = (Attack) o;
        return id == attack.id && Double.compare(damage, attack.damage) == 0 && Objects.equals(name, attack.name) && Objects.equals(type, attack.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, damage, type);
    }

    @Override
    public String toString() {
        return "Attack{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", damage=" + damage +
                ", type='" + type + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
