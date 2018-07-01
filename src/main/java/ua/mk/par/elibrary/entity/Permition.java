package ua.mk.par.elibrary.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "Permition")
@Table(name = "\"permition\"")
@NamedQuery(name = "Permition.getAll", query = "SELECT p from Permition p")
public class Permition implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    public Permition() {
    }

    public Permition(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permition permition = (Permition) o;
        return Objects.equals(id, permition.id) &&
                Objects.equals(name, permition.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Permition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
