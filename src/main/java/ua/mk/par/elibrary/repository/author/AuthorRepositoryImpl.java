package ua.mk.par.elibrary.repository.author;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.mk.par.elibrary.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class AuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Author getById(Long id) {
        if (id == null){
            return null;
        }
        return em.find(Author.class, id);
    }

    @Override
    public List<Author> getAll(Boolean forward, Long first, Integer max) {
        String q;
        if (forward) {
            q = "FROM Author WHERE id > :first ORDER BY id";
        }
        else {
            q = "FROM Author WHERE id < :first ORDER BY id DESC";
        }
        return em.createQuery(q)
                .setParameter("first", first)
                .setMaxResults(max)
                .getResultList();
    }

    @Override
    public Author create(Author author) {
        em.persist(author);
        return author;
    }

    @Override
    public Author update(Author author) {
        return em.merge(author);
    }

    @Override
    public void delete(Long id) {
        em.remove(getById(id));
    }
}