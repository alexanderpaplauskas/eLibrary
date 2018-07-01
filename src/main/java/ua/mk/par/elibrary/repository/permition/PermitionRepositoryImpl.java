package ua.mk.par.elibrary.repository.permition;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.mk.par.elibrary.entity.Permition;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class PermitionRepositoryImpl implements PermitionRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Permition getById(Long id) {
        if (id == null){
            return null;
        }
        return  em.find(Permition.class, id);
    }

    @Override
    public List<Permition> getAll(Boolean forward, Long first, Integer max) {
        String q;
        if (forward) {
            q = "FROM Permition WHERE id > :first ORDER BY id";
        }
        else {
            q = "FROM Permition WHERE id < :first ORDER BY id DESC";
        }
        return em.createQuery(q)
                .setParameter("first", first)
                .setMaxResults(max)
                .getResultList();
    }

    @Override
    public Permition create(Permition permition) {
        em.persist(permition);
        return permition;
    }

    @Override
    public Permition update(Permition permition) {
        return em.merge(permition);
    }

    @Override
    public void delete(Long id) {
        em.remove(getById(id));
    }
}
