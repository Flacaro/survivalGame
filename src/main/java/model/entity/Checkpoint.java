package model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CHECKPOINT")
public class Checkpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "EXP", nullable = false)
    private int exp;

    @OneToOne
    @JoinColumn(name = "id_skill", referencedColumnName = "id")
    private Skill skill;

    public Checkpoint() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

}