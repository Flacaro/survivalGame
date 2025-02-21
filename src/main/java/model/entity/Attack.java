package model.entity;

import jakarta.persistence.*;


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

}
