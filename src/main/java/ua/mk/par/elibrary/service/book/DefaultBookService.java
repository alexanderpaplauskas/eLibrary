package ua.mk.par.elibrary.service.book;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

import ua.mk.par.elibrary.controller.book.forms.EditBookForm;
import ua.mk.par.elibrary.entity.Author;
import ua.mk.par.elibrary.entity.Book;
import ua.mk.par.elibrary.entity.Category;
import ua.mk.par.elibrary.repository.author.AuthorRepository;
import ua.mk.par.elibrary.repository.book.BookRepository;
import ua.mk.par.elibrary.repository.category.CategoryRepository;
import ua.mk.par.elibrary.repository.publisher.PublisherRepository;

@Service
@Transactional
public class DefaultBookService implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;

    public DefaultBookService(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.getById(id);
    }

    @Override
    public EditBookForm getFormById(Long id) {
        if (id == 0l) {
            EditBookForm eu = new EditBookForm();
            return eu;
        }
        Book book = getById(id);
        if (book != null) {
            EditBookForm ebook = new EditBookForm();
            ebook.setId(book.getId());
            ebook.setName(book.getName());

            List<String> formAuthor = new ArrayList<>();
            for (Author author : book.getAutors()) {
                formAuthor.add("{\"id\":\"" + author.getId() + "\",\"name\":\"" + author.getFirstName() + " " + author.getLastName() + "\"}");
            }
            ebook.setAuthors(formAuthor);

            List<String> formCategory = new ArrayList<>();
            for (Category category : book.getCategories()) {
                formCategory.add("{\"id\":\"" + category.getId() + "\",\"name\":\"" + category.getName() + "\"}");
            }
            ebook.setCategories(formCategory);

            ebook.setDescription(book.getDecription());
            ebook.setLanguage(book.getLanguage());
            ebook.setPublisher("{\"id\":\"" + book.getPublisher().getId() + "\",\"name\":\"" + book.getPublisher().getName() + "\"}");
            ebook.setPublisher_date(book.getPublisherDate());
            ebook.setPages(book.getPages());
            return ebook;
        }
        return null;
    }

    @Override
    public List<EditBookForm> getAll(Boolean forward, Long first, int max) {
        List<Book> books = bookRepository.getAll(forward, first, max);
        List<EditBookForm> formBookList = new ArrayList<>();
        for (Book book : books) {
            EditBookForm formBook = new EditBookForm();
            formBook.setId(book.getId());
            formBook.setName(book.getName());

            List<String> formAuts = new ArrayList<>();
            for (Author author : book.getAutors()) {
                formAuts.add(author.getFirstName() + " " + author.getLastName());
            }
            formBook.setAuthors(formAuts);

            List<String> formCats = new ArrayList<>();
            for (Category category : book.getCategories()) {
                formCats.add(category.getName());
            }
            formBook.setCategories(formCats);

            formBook.setPublisher(book.getPublisher().getName());
            formBook.setPublisher_date(book.getPublisherDate());
            formBook.setLanguage(book.getLanguage());
            formBook.setPages(book.getPages());
            formBook.setDescription(book.getDecription());
            formBookList.add(formBook);
        }
        if (!forward) {
            Collections.sort(formBookList, new Comparator<EditBookForm>() {
                @Override
                public int compare(EditBookForm o1, EditBookForm o2) {
                    return o1.getId().compareTo(o2.getId());
                }
            });
        }
        return formBookList;
    }

    @Override
    public EditBookForm create(EditBookForm editBookForm) throws IOException {
        Book book = new Book();
        book.setName(editBookForm.getName());
        book.setPublisherDate(editBookForm.getPublisher_date());
        book.setLanguage(editBookForm.getLanguage());
        book.setPages(editBookForm.getPages());
        book.setDecription(editBookForm.getDescription());
        bookRepository.create(book);
        editBookForm.setId(book.getId());
        if (book.getId()==null) { return editBookForm;
        }
        if ((editBookForm.getAuthors() != null) || (editBookForm.getCategories() != null) || (editBookForm.getPublisher() != null)) {
            update(editBookForm);
        }
        return editBookForm;
    }

    @Override
    public void update(EditBookForm editBookForm) throws IOException {
        if (editBookForm.getId() == null) {
            create(editBookForm);
            return;
        }
        Book book = bookRepository.getById(editBookForm.getId());
        if (book == null) {
            return;
        } else {
            book.setId(editBookForm.getId());
            book.setName(editBookForm.getName());

            Set<Author> list_aut = new TreeSet<>();
            for (String aut : editBookForm.getAuthors()) {
                if (!aut.isEmpty()) {
                    Long cur_id = Long.parseLong(aut, 10);
                    list_aut.add(authorRepository.getById(cur_id));
                }
            }
            book.setAutors(list_aut);

            Set<Category> list_cat = new TreeSet<>();
            for (String cat : editBookForm.getCategories()) {
                if (!cat.isEmpty()) {
                    Long cur_id = Long.parseLong(cat, 10);
                    list_cat.add(categoryRepository.getById(cur_id));
                }
            }
            book.setCategories(list_cat);
            Long pub_id = Long.parseLong(editBookForm.getPublisher(), 10);
            book.setPublisher(publisherRepository.getById(pub_id));
            book.setPublisherDate(editBookForm.getPublisher_date());
            book.setLanguage(editBookForm.getLanguage());
            book.setPages(editBookForm.getPages());
            book.setDecription(editBookForm.getDescription());
            bookRepository.update(book);
        }
    }

    @Override
    public void delete(Long id) {
        bookRepository.delete(id);

    }
}
