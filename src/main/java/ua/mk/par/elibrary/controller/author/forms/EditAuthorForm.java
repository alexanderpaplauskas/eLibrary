package ua.mk.par.elibrary.controller.author.forms;

public class EditAuthorForm {
    Long id;
    String first_name;
    String last_name;
    String description;

    public EditAuthorForm() {
    }

    public EditAuthorForm(Long id, String first_name, String last_name, String description) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "EditAuthorForm{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
