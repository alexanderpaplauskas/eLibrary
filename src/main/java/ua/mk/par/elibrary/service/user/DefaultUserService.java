package ua.mk.par.elibrary.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.util.*;

import ua.mk.par.elibrary.controller.user.forms.CreateUserForm;
import ua.mk.par.elibrary.controller.user.forms.EditUserForm;
import ua.mk.par.elibrary.controller.user.forms.LoginForm;
import ua.mk.par.elibrary.entity.Role;
import ua.mk.par.elibrary.entity.User;
import ua.mk.par.elibrary.repository.Constants;
import ua.mk.par.elibrary.repository.role.RoleRepository;
import ua.mk.par.elibrary.repository.role.RoleRepositoryImpl;
import ua.mk.par.elibrary.repository.user.UserRepository;
import ua.mk.par.elibrary.repository.user.UserRepositoryImpl;
import ua.mk.par.elibrary.service.role.DefaultRoleService;

@Service
@Transactional
public class DefaultUserService implements UserService, UserDetailsService {

    private final UserRepositoryImpl userRepository;
    private final RoleRepositoryImpl roleRepository;

    public DefaultUserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = (UserRepositoryImpl) userRepository;
        this.roleRepository = (RoleRepositoryImpl) roleRepository;
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public EditUserForm getFormById(Long id) {
        if (id == 0l){
            EditUserForm eu = new EditUserForm();
            return eu;
        }
        User u = getById(id);
        if (u != null) {
            EditUserForm eu = new EditUserForm();
            eu.setId(u.getId());
            eu.setName(u.getName());
            eu.setEmail(u.getEmail());
            eu.setPassword(u.getPassword());
            eu.setBirth_date(u.getBirthDate());
            List<String> formRoles = new ArrayList<>();
            for (Role role : u.getRoles()){
                formRoles.add("{\"id\":\""+role.getId()+"\",\"name\":\""+role.getName()+"\"}");
            }
            eu.setRoles(formRoles);
            return eu;
        }
        return null;
    }

    @Override
    public List<EditUserForm> getAll(Boolean forward, Long first, int max) {
        List<User> users = userRepository.getAll(forward, first, max);
        List<EditUserForm> formUsers = new ArrayList<>();
        for ( User user : users ){
            EditUserForm formUser = new EditUserForm();
            formUser.setId(user.getId());
            formUser.setName(user.getName());
            formUser.setEmail(user.getEmail());
            formUser.setPassword(user.getPassword());
            formUser.setBirth_date(user.getBirthDate());
            List<String> formRoles = new ArrayList<>();
            for (Role role : user.getRoles()){
                formRoles.add(role.getName());
            }
            formUser.setRoles(formRoles);
            formUsers.add(formUser);
        }
        if (!forward){
        Collections.sort(formUsers, new Comparator<EditUserForm>() {
            @Override
            public int compare(EditUserForm o1, EditUserForm o2) {
                return o1.getId().compareTo(o2.getId());
            }
        } );}
        return formUsers;
    }

    @Override
    public Boolean checkLoginPassword(String login, String password) {
        User u = userRepository.getByParam("name",login);
        if (u == null) {
            return false;
        }
        return (u.getPassword().compareTo(password)==0);
    }

    @Override
    public User findUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public void createByUser(CreateUserForm createUserForm) throws IOException {
        User newUser = new User();
        newUser.setName(createUserForm.getName());
        newUser.setEmail(createUserForm.getEmail());
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        newUser.setPassword(encoder.encode(createUserForm.getPassword()));
        newUser.setBirthDate(createUserForm.getBirth_date());
        userRepository.create(newUser);
        newUser.setRoles(new TreeSet<>());
        newUser.getRoles().add(Constants.getAllUserRole());
        userRepository.update(newUser);
    }

    @Override
    public EditUserForm create(EditUserForm editUserForm) throws IOException {
        User user = new User();
        user.setName(editUserForm.getName());
        userRepository.create(user);
        editUserForm.setId(user.getId());
        if (user.getId()==null) { return editUserForm;
        }
        if (editUserForm.getRoles() != null) {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            editUserForm.setPassword(encoder.encode(editUserForm.getPassword()));
            update(editUserForm);
        }
        return editUserForm;
    }

    @Override
    public void update(EditUserForm editUserForm) throws IOException  {
        if (editUserForm.getId()==null){
            create(editUserForm);
            return;
        }
        User user = userRepository.getById(editUserForm.getId());
        if (user == null) {
            return;
        } else {
            user.setName(editUserForm.getName());
            user.setEmail(editUserForm.getEmail());
            if (! user.getPassword().equals(editUserForm.getPassword())){
                PasswordEncoder encoder = new BCryptPasswordEncoder();
                user.setPassword(encoder.encode(editUserForm.getPassword()));
            }
            user.setBirthDate(editUserForm.getBirth_date());
            Set<Role> list_role = new TreeSet<>();
            for (String erole : editUserForm.getRoles()){
                if (!erole.isEmpty()){
                    Long cur_id = Long.parseLong(erole, 10);
                    list_role.add(roleRepository.getById(cur_id));
                }}
            user.setRoles(list_role);
            userRepository.update(user);
        }
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);

    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User u = findUserByName(userName);
        if (u == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
            for (Role role : u.getRoles()) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
                grantList.add(authority);
            }
        UserDetails userDetails = new LoginForm(u.getName(), u.getPassword(), grantList);
        return userDetails;
    }
}
