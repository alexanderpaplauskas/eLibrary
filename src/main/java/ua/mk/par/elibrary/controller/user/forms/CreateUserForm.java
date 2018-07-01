package ua.mk.par.elibrary.controller.user.forms;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

public class CreateUserForm {
    String name;
    String email;
    String password;
    String confirm_password;
    Date birth_date;

    public CreateUserForm() {
    }

    public CreateUserForm(String name, String email, String password, String confirm_password, Date birth_date) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirm_password = confirm_password;
        this.birth_date = birth_date;
    }

    @Override
    public String toString() {
        return "CreateUserForm{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirm_password='" + confirm_password + '\'' +
                ", birth_date=" + birth_date +
                '}';
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

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getBirth_date() {
        return birth_date;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }
}
