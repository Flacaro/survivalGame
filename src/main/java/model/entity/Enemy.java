package model.entity;

import jakarta.persistence.*;


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

}