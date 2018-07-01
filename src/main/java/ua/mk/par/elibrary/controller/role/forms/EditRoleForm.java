package ua.mk.par.elibrary.controller.role.forms;

public class EditRoleForm {
    Long id;
    String name;
    String description;

    public EditRoleForm() {
    }

    public EditRoleForm(Long id, String name, String decription) {
        this.id = id;
        this.name = name;
        this.description = decription;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String decription) {
        this.description = decription;
    }

    @Override
    public String toString() {
        return "EditRoleForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
