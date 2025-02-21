package model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CRAFTED_RESOURCE")
public class CraftedResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "CATEGORY", nullable = false)
    private String category;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attack> attacks=new ArrayList<>();

    @Column(name = "LEVEL", nullable = false)
    private int level = 1;


}
