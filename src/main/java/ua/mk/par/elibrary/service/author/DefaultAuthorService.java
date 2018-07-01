package ua.mk.par.elibrary.service.author;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ua.mk.par.elibrary.controller.author.forms.EditAuthorForm;
import ua.mk.par.elibrary.entity.Author;
import ua.mk.par.elibrary.repository.author.AuthorRepository;

@Service
@Transactional
public class DefaultAuthorService implements AuthorService {

    private final AuthorRepository authorRepository;

    public DefaultAuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getById(Long id) {
        return authorRepository.getById(id);
    }

    @Override
    public EditAuthorForm getFormById(Long id) {
        if (id == 0l){
            EditAuthorForm eu = new EditAuthorForm();
            return eu;
        }
        Author a = getById(id);
        if (a != null) {
            EditAuthorForm eu = new EditAuthorForm();
            eu.setId(a.getId());
            eu.setFirst_name(a.getFirstName());
            eu.setLast_name(a.getLastName());
            eu.setDescription(a.getDescription());
            return eu;
        }
        return null;
    }

    @Override
    public List<EditAuthorForm> getAll(Boolean forward, Long first, int max) {
        List<Author> autors = authorRepository.getAll(forward, first, max);
        List<EditAuthorForm> formAuthors = new ArrayList<>();
        for (Author author : autors) {
            EditAuthorForm formAuthor = new EditAuthorForm();
            formAuthor.setId(author.getId());
            formAuthor.setFirst_name(author.getFirstName());
            formAuthor.setLast_name(author.getLastName());
            formAuthor.setDescription(author.getDescription());
            formAuthors.add(formAuthor);
        }
        if (!forward) {
            Collections.sort(formAuthors, new Comparator<EditAuthorForm>() {
                @Override
                public int compare(EditAuthorForm o1, EditAuthorForm o2) {
                    return o1.getId().compareTo(o2.getId());
                }
            });
        }
        return formAuthors;
    }

    @Override
    public EditAuthorForm create(EditAuthorForm editAuthorForm) throws IOException {
        Author author = new Author();
        author.setFirstName(editAuthorForm.getFirst_name());
        author.setLastName(editAuthorForm.getLast_name());
        author.setDescription(editAuthorForm.getDescription());
        authorRepository.create(author);
        editAuthorForm.setId(author.getId());
        return editAuthorForm;
    }

    @Override
    public void update(EditAuthorForm editAuthorForm) throws IOException {
        if (editAuthorForm.getId()==null){
            create(editAuthorForm);
            return;
        }
        Author author = authorRepository.getById(editAuthorForm.getId());
        if (author == null) {
            return;
        } else {
            author.setId(editAuthorForm.getId());
            author.setFirstName(editAuthorForm.getFirst_name());
            author.setLastName(editAuthorForm.getLast_name());
            author.setDescription(editAuthorForm.getDescription());
            authorRepository.update(author);
        }
    }


        @Override
        public void delete (Long id){
            authorRepository.delete(id);
        }
    }
