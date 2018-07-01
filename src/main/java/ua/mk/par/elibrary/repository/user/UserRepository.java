package ua.mk.par.elibrary.repository.user;

import java.util.List;

import ua.mk.par.elibrary.entity.User;

public interface UserRepository {

    public User getById(Long id);

    public User getByParam(String paramName, String param);

    public List<User> getAll(Boolean forward, Long first, Integer max);

    public User create(User user);

    public User update(User user);

    public void delete(Long id);

    User findByName(String name);
}
