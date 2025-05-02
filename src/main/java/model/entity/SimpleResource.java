package model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "SIMPLE_RESOURCE")
public class SimpleResource extends Event implements Resource {

    @Column(name = "CATEGORY", nullable = false)
    private String category;

    //test 1 a molti con attack
    @OneToMany
    private List<Attack> attacks;


    public SimpleResource() {
    }

    public SimpleResource(String category, List<Attack> attacks, int level, String name, int quantity, String type) {
        this.category = category;
        this.attacks = attacks;
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


    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public void setDescription(String description) {

    }


    @Override
    public void use() {

    }


    @Override
    public void setId(Long id) {

    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SimpleResource that = (SimpleResource) o;
        return Objects.equals(category, that.category) && Objects.equals(attacks, that.attacks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, attacks);
    }
}
