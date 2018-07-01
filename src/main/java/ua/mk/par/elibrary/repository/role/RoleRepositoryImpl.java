package ua.mk.par.elibrary.repository.role;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.mk.par.elibrary.entity.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager em;

    public RoleRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Role getById(Long id) {
        if (id == null){
            return null;
        }
        return  em.find(Role.class, id);
    }

    @Override
    public List<Role> getAll(Boolean forward, Long first, Integer max) {
        String q;
        if (forward) {
            q = "FROM Role WHERE id > :first ORDER BY id";
        }
        else {
            q = "FROM Role WHERE id < :first ORDER BY id DESC";
        }
        return em.createQuery(q)
                .setParameter("first", first)
                .setMaxResults(max)
                .getResultList();
    }

    @Override
    public Role create(Role role) {
        em.persist(role);
        return role;
    }

    @Override
    public Role update(Role role) {
        return em.merge(role);
    }

    @Override
    public void delete(Long id) {
        em.remove(getById(id));
    }
}
