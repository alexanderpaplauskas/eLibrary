package ua.mk.par.elibrary.controller.permition.forms;

public class EditPermitionForm {
    Long id;
    String name;

    public EditPermitionForm() {
    }

    public EditPermitionForm(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "EditPermitionForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
