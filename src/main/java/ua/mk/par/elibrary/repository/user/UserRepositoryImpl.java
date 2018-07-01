package ua.mk.par.elibrary.repository.user;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.mk.par.elibrary.entity.Role;
import ua.mk.par.elibrary.entity.User;
import ua.mk.par.elibrary.repository.Constants;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User getById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User getByParam(String paramName, String param) {
        Constants.initAllConstants(em);
        Query query = em.createQuery("from User where " + paramName + " = :param");
        query.setMaxResults(1);
        query.setParameter("param", param);
        List results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else {
            return (User) results.get(0);
        }
    }

    @Override
    public List<User> getAll(Boolean forward, Long first, Integer max) {
        String q;
        if (forward) {
            q = "FROM User WHERE id > :first ORDER BY id";
        } else {
            q = "FROM User WHERE id < :first ORDER BY id DESC";
        }
        return em.createQuery(q)
                .setParameter("first", first)
                .setMaxResults(max)
                .getResultList();
    }

    @Override
    public User create(User user) {
        Constants.initAllConstants(em);
        user.setRoles(null);
        em.persist(user);
        return user;
    }

    @Override
    public User update(User user) {
        return em.merge(user);
    }

    @Override
    public void delete(Long id) {
        em.remove(getById(id));
    }

    @Override
    public User findByName(String name) {
        String q;
        q = "FROM User WHERE name = :name";
        List<User> lu = em.createQuery(q)
                .setParameter("name", name)
                .setMaxResults(1)
                .getResultList();
        if ((lu == null) || lu.isEmpty()) {
            return null;
        }
        else {
            return lu.get(0);
        }
    }
}
