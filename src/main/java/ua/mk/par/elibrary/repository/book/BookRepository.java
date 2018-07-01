package ua.mk.par.elibrary.repository.book;

import java.util.List;

import ua.mk.par.elibrary.entity.Book;

public interface BookRepository {

    public Book getById(Long id);

    public List<Book> getAll(Boolean forward, Long first, Integer max);

    public Book create(Book book);

    public Book update(Book book);

    public void delete(Long id);
}
