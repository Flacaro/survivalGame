package model.entity;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "ENEMY")
public class Enemy extends Event {

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_mode", referencedColumnName = "id")
    private Mode mode;

    @Column(name = "HEALTH", nullable = false)
    private double health;

    public Enemy() {
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Enemy enemy = (Enemy) o;
        return Double.compare(health, enemy.health) == 0 && Objects.equals(mode, enemy.mode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mode, health);
    }
}