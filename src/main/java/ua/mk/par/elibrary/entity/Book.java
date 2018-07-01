package ua.mk.par.elibrary.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity(name = "Book")
@Table(name = "\"book\"")
@NamedQuery(name = "Book.getAll", query = "SELECT b from Book b")
public class Book implements Serializable, Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @ManyToMany()
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> autors = new TreeSet<>();

    @ManyToMany()
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new TreeSet<>();

    @Column(name = "decription", nullable = true, length = 200)
    private String decription;

    @Column(name = "pages")
    private Integer pages;

    @ManyToOne()
    private Publisher publisher;

    @Column(name = "publisher_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publisherDate;

    @Column(name = "language", nullable = true, unique = false, length = 20)
    private String language;

    public Book() {
    }

    public Book(Long id, String name, Set<Author> autors, Set<Category> categories, String decription, Integer pages, Publisher publisher, Date publisherDate, String language) {
        this.id = id;
        this.name = name;
        this.autors = autors;
        this.categories = categories;
        this.decription = decription;
        this.pages = pages;
        this.publisher = publisher;
        this.publisherDate = publisherDate;
        this.language = language;
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

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Date getPublisherDate() {
        return publisherDate;
    }

    public void setPublisherDate(Date publisherDate) {
        this.publisherDate = publisherDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(name, book.name) &&
                Objects.equals(autors, book.autors) &&
                Objects.equals(categories, book.categories) &&
                Objects.equals(decription, book.decription) &&
                Objects.equals(pages, book.pages) &&
                Objects.equals(publisher, book.publisher) &&
                Objects.equals(publisherDate, book.publisherDate) &&
                Objects.equals(language, book.language);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, autors, categories, decription, pages, publisher, publisherDate, language);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categories=" + categories +
                ", decription='" + decription + '\'' +
                ", pages=" + pages +
                ", publisher=" + publisher +
                ", publisherDate=" + publisherDate +
                ", language='" + language + '\'' +
                '}';
    }

    public Set<Author> getAutors() {
        return autors;
    }

    public void setAutors(Set<Author> autors) {
        this.autors = autors;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) return 0;
        return name.compareTo(((Book) o).name);
    }
}
