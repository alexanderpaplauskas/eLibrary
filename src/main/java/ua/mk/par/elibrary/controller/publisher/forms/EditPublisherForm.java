package ua.mk.par.elibrary.controller.publisher.forms;

public class EditPublisherForm {
    Long id;
    String name;
    String description;

    public EditPublisherForm() {
    }

    public EditPublisherForm(Long id, String name, String decription) {
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
        return "EditPublisherForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
