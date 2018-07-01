package ua.mk.par.elibrary.repository.book;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.mk.par.elibrary.entity.Author;
import ua.mk.par.elibrary.entity.Book;
import ua.mk.par.elibrary.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book getById(Long id) {
        if (id == null){
            return null;
        }
        return  em.find(Book.class, id);
    }

    @Override
    public List<Book> getAll(Boolean forward, Long first, Integer max) {
        String q;
        if (forward) {
            q = "FROM Book WHERE id > :first ORDER BY id";
        }
        else {
            q = "FROM Book WHERE id < :first ORDER BY id DESC";
        }
        return em.createQuery(q)
                .setParameter("first", first)
                .setMaxResults(max)
                .getResultList();
    }

    @Override
    public Book create(Book book) {
        book.setCategories(null);
        book.setAutors(null);
        book.setPublisher(null);
        em.persist(book);
        return book;
    }

    @Override
    public Book update(Book book) {
        return em.merge(book);
    }

    @Override
    public void delete(Long id) {
        em.remove(getById(id));
    }
}
