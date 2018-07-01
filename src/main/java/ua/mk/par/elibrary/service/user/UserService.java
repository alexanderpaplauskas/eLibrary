package ua.mk.par.elibrary.service.user;

import java.io.IOException;
import java.util.List;

import ua.mk.par.elibrary.controller.user.forms.CreateUserForm;
import ua.mk.par.elibrary.controller.user.forms.EditUserForm;
import ua.mk.par.elibrary.entity.User;

public interface UserService {

    public User getById(Long id);

    public EditUserForm getFormById(Long id);

    public void createByUser(CreateUserForm createUserForm) throws IOException;

    public EditUserForm create(EditUserForm editUserForm) throws IOException;

    public void update(EditUserForm editUserForm) throws IOException;

    public void delete(Long id);

    public List<EditUserForm> getAll(Boolean forward, Long first, int max);

    public Boolean checkLoginPassword(String login, String password);

    public User findUserByName(String name);
}
