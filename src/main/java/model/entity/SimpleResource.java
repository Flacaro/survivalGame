package model.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "SIMPLE_RESOURCE")
public class SimpleResource extends Event implements Resource {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(name = "CATEGORY", nullable = false)
        private String category;

        //test 1 a molti con attack
        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Attack> attacks;

        @Column(name = "LEVEL", nullable = false)
        private int level = 1;

        private String name;

        private int quantity;

        private String type;

        public SimpleResource() {
        }

        public SimpleResource(long id, String category, List<Attack> attacks, int level, String name, int quantity, String type) {
            this.id = id;
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

        public List<Attack> getAttacks() {
            return attacks;
        }

        public void setAttacks(List<Attack> attacks) {
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

    @Override
    public void use() {

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

    @Override
    public String toString() {
        return "SimpleResource{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", attacks=" + attacks +
                ", level=" + level +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SimpleResource that = (SimpleResource) o;
        return id == that.id && level == that.level && quantity == that.quantity && Objects.equals(category, that.category) && Objects.equals(attacks, that.attacks) && Objects.equals(name, that.name) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, attacks, level, name, quantity, type);
    }
}
