package ua.mk.par.elibrary.service.role;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ua.mk.par.elibrary.controller.role.forms.EditRoleForm;
import ua.mk.par.elibrary.entity.Role;
import ua.mk.par.elibrary.repository.role.RoleRepository;

@Service
@Transactional
public class DefaultRoleService implements RoleSerivice {

    private final RoleRepository roleRepository;

    public DefaultRoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getById(Long id) {
        return roleRepository.getById(id);
    }

    @Override
    public EditRoleForm getFormById(Long id) {
        if (id == 0l){
            EditRoleForm eu = new EditRoleForm();
            return eu;
        }
        Role a = getById(id);
        if (a != null) {
            EditRoleForm  eu = new EditRoleForm();
            eu.setId(a.getId());
            eu.setName(a.getName());
            eu.setDescription(a.getDescription());
            return eu;
        }
        return null;
    }

    @Override
    public List<EditRoleForm> getAll(Boolean forward, Long first, int max) {
        List<Role> roles = roleRepository.getAll(forward, first, max);
        List<EditRoleForm> formRoleList = new ArrayList<>();
        for (Role role : roles) {
            EditRoleForm formRole = new EditRoleForm();
            formRole.setId(role.getId());
            formRole.setName(role.getName());
            formRole.setDescription(role.getDescription());
            formRoleList.add(formRole);
        }
        if (!forward) {
            Collections.sort(formRoleList, new Comparator<EditRoleForm>() {
                @Override
                public int compare(EditRoleForm o1, EditRoleForm o2) {
                    return o1.getId().compareTo(o2.getId());
                }
            });
        }
        return formRoleList;
    }

    @Override
    public EditRoleForm create(EditRoleForm editRoleForm) throws IOException {
        Role role = new Role();
        role.setName(editRoleForm.getName());
        role.setDescription(editRoleForm.getDescription());
        roleRepository.create(role);
        editRoleForm.setId(role.getId());
        return editRoleForm;
    }

    @Override
    public void update(EditRoleForm editRoleForm) throws IOException {
        if (editRoleForm.getId()==null){
            create(editRoleForm);
            return;
        }
        Role role = roleRepository.getById(editRoleForm.getId());
        if (role == null) {
            return;
        } else {
            role.setId(editRoleForm.getId());
            role.setName(editRoleForm.getName());
            role.setDescription(editRoleForm.getDescription());
            roleRepository.update(role);
        }
    }

    @Override
    public void delete(Long id) {
        roleRepository.delete(id);

    }
}
