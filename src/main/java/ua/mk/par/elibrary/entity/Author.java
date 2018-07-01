package ua.mk.par.elibrary.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity(name = "Author")
@Table(name = "\"author\"")
@NamedQuery(name = "Author.getAll", query = "SELECT a from Author a")
public class Author implements Serializable, Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    @Column(name = "first_name",nullable = false,unique = false,length = 50)
    private String firstName;

    @Column(name = "last_name",nullable = false,unique = false,length = 50)
    private String lastName;

    @Column(name = "description",nullable = true,unique = false,length = 200)
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="book_author",
            joinColumns=@JoinColumn(name="author_id"),
            inverseJoinColumns=@JoinColumn(name="book_id"))
    private Set<Book> books = new TreeSet<>();

    public Author() {
    }

    public Author(Long id, String firstName, String lastName, String description) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id) &&
                Objects.equals(firstName, author.firstName) &&
                Objects.equals(lastName, author.lastName) &&
                Objects.equals(description, author.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, lastName, description);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setCategories(Set<Book> books) {
        this.books = books;
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) return 0;
        return (lastName+firstName).compareTo(((Author)o).lastName+((Author)o).firstName);
    }
}
