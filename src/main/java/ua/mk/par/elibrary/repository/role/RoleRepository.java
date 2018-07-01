package ua.mk.par.elibrary.repository.role;

import java.util.List;

import ua.mk.par.elibrary.entity.Role;

public interface RoleRepository {

    public Role getById(Long id);

    public List<Role> getAll(Boolean forward, Long first, Integer max);

    public Role create(Role role);

    public Role update(Role role);

    public void delete(Long id);
}
