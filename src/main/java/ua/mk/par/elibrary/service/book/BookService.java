package ua.mk.par.elibrary.service.book;

import java.io.IOException;
import java.util.List;

import ua.mk.par.elibrary.controller.book.forms.EditBookForm;
import ua.mk.par.elibrary.entity.Book;

public interface BookService {

    public Book getById(Long id);

    public EditBookForm getFormById(Long id);

    public EditBookForm create(EditBookForm editBookForm) throws IOException;

    public void update(EditBookForm editBookForm) throws IOException;

    public void delete(Long id);

    public List<EditBookForm> getAll(Boolean forward, Long first, int max);
}
