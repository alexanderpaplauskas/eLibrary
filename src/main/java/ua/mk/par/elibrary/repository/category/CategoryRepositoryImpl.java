package ua.mk.par.elibrary.repository.category;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.mk.par.elibrary.entity.Category;
import ua.mk.par.elibrary.entity.Permition;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Category getById(Long id) {
        if (id == null){
            return null;
        }
        return  em.find(Category.class, id);
    }

    @Override
    public List<Category> getAll(Boolean forward, Long first, Integer max) {
        String q;
        if (forward) {
            q = "FROM Category WHERE id > :first ORDER BY id";
        }
        else {
            q = "FROM Category WHERE id < :first ORDER BY id DESC";
        }
        return em.createQuery(q)
                .setParameter("first", first)
                .setMaxResults(max)
                .getResultList();
    }

    @Override
    public Category create(Category category) {
        category.setPermitions(null);
        em.persist(category);
        return category;
    }

    @Override
    public Category update(Category category) {
        return em.merge(category);
    }

    @Override
    public void delete(Long id) {
        em.remove(getById(id));
    }
}
