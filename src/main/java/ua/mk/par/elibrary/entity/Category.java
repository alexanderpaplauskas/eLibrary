package ua.mk.par.elibrary.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Category")
@Table(name = "\"category\"")
@NamedQuery(name = "Category.getAll", query = "SELECT c from Category c")
public class Category implements Serializable, Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "description", nullable = true, unique = false, length = 200)
    private String description;

    @ManyToMany()
    @JoinTable(
            name = "category_permition",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "permition_id"))
    private List<Permition> permitions = new ArrayList<>();

    public Category() {
    }

    public Category(Long id, String name, String description, List<Permition> permitions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.permitions = permitions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Permition> getPermitions() {
        return permitions;
    }

    public void setPermitions(List<Permition> permitions) {
        this.permitions = permitions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(name, category.name) &&
                Objects.equals(description, category.description) &&
                Objects.equals(permitions, category.permitions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, permitions);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", permitions=" + permitions +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) return 0;
        return name.compareTo(((Category) o).name);
    }
}
