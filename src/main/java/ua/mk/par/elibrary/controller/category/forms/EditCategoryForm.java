package ua.mk.par.elibrary.controller.category.forms;

import ua.mk.par.elibrary.controller.permition.forms.EditPermitionForm;

import java.util.List;

public class EditCategoryForm {
    Long id;
    String name;
    List<String> permitions;
    String description;

    public EditCategoryForm() {
    }

    public EditCategoryForm(Long id, String name, List<String> permitions, String decription) {
        this.id = id;
        this.name = name;
        this.permitions = permitions;
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

    public List<String> getPermitions() {
        return permitions;
    }

    public void setPermitions(List<String> permitions) {
        this.permitions = permitions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String decription) {
        this.description = decription;
    }

    @Override
    public String toString() {
        return "EditCategoryForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", permitions='" + permitions + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
