package model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "SIMPLE_RESOURCE")
public class SimpleResource extends Event implements Resource {

    @Column(name = "CATEGORY", nullable = false)
    private String category;

    public SimpleResource() {
    }

    public SimpleResource(String category, int level, String name, int quantity, String type) {
        this.category = category;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
        return Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category);
    }
}
