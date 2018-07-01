package ua.mk.par.elibrary.repository.author;

import java.util.List;

import ua.mk.par.elibrary.entity.Author;

public interface AuthorRepository {

    public Author getById(Long id);

    public List<Author> getAll(Boolean forward, Long first, Integer max);

    public Author create(Author author);

    public Author update(Author author);

    public void delete(Long id);
}
