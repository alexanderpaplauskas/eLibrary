package ua.mk.par.elibrary.repository.publisher;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.mk.par.elibrary.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class PublisherRepositoryImpl implements PublisherRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Publisher getById(Long id) {
        if (id == null){
            return null;
        }
        return  em.find(Publisher.class, id);
    }

    @Override
    public List<Publisher> getAll(Boolean forward, Long first, Integer max) {
        String q;
        if (forward) {
            q = "FROM Publisher WHERE id > :first ORDER BY id";
        }
        else {
            q = "FROM Publisher WHERE id < :first ORDER BY id DESC";
        }
        return em.createQuery(q)
                .setParameter("first", first)
                .setMaxResults(max)
                .getResultList();
    }

    @Override
    public Publisher create(Publisher publisher) {
        em.persist(publisher);
        return publisher;
    }

    @Override
    public Publisher update(Publisher publisher) {
        return em.merge(publisher);
    }

    @Override
    public void delete(Long id) {
        em.remove(getById(id));
    }
}
