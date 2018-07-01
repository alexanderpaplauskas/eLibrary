package ua.mk.par.elibrary.controller.user.forms;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class EditUserForm {
    Long id;
    String name;
    String email;
    String password;
    Date birth_date;
    List<String> roles;

    public EditUserForm() {
    }

    public EditUserForm(Long id, String name, String email, String password, Date birth_date, List<String> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birth_date = birth_date;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getBirth_date() {
        return birth_date;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "EditUserForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birth_date=" + birth_date +
                ", roles=" + roles +
                '}';
    }
}
