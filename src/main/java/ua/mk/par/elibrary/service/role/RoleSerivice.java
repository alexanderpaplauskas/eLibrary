package ua.mk.par.elibrary.service.role;

import java.io.IOException;
import java.util.List;

import ua.mk.par.elibrary.controller.role.forms.EditRoleForm;
import ua.mk.par.elibrary.entity.Role;

public interface RoleSerivice {

    public Role getById(Long id);

    public EditRoleForm getFormById(Long id);

    public EditRoleForm create(EditRoleForm editRoleForm) throws IOException;

    public void update(EditRoleForm editRoleForm) throws IOException;

    public void delete(Long id);

    public List<EditRoleForm> getAll(Boolean forward, Long first, int max);
}
