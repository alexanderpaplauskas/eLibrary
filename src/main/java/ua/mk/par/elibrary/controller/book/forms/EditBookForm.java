package ua.mk.par.elibrary.controller.book.forms;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class EditBookForm {
    Long id;
    String name;
    List<String> authors;
    List<String> categories;
    String description;
    Integer pages;
    Date publisher_date;
    String publisher;
    String language;

    public EditBookForm() {
    }

    public EditBookForm(Long id, String name, List<String> authors, List<String> categories, String description, Integer pages, Date publisherDate, String publisher, String language) {
        this.id = id;
        this.name = name;
        this.authors = authors;
        this.categories = categories;
        this.description = description;
        this.pages = pages;
        this.publisher_date = publisherDate;
        this.publisher = publisher;
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

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String decription) {
        this.description = decription;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getPublisher_date() {
        return publisher_date;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setPublisher_date(Date publisherDate) {
        this.publisher_date = publisherDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "EditBookForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", autors='" + authors + '\'' +
                ", categories='" + categories + '\'' +
                ", decription='" + description + '\'' +
                ", pages=" + pages +
                ", publisherDate=" + publisher_date +
                ", publisher='" + publisher + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
