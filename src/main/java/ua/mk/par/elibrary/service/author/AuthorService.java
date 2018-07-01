package ua.mk.par.elibrary.service.author;

import java.io.IOException;
import java.util.List;

import ua.mk.par.elibrary.controller.author.forms.EditAuthorForm;
import ua.mk.par.elibrary.entity.Author;

public interface AuthorService {

    public Author getById(Long id);

    public EditAuthorForm getFormById(Long id);

    public EditAuthorForm create(EditAuthorForm editAuthorForm) throws IOException;

    public void update(EditAuthorForm editAuthorForm) throws IOException;

    public void delete(Long id);

    public List<EditAuthorForm> getAll(Boolean forward, Long first, int max);

}
