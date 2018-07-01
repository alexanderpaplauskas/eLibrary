package ua.mk.par.elibrary.service.category;

import java.io.IOException;
import java.util.List;

import ua.mk.par.elibrary.controller.category.forms.EditCategoryForm;
import ua.mk.par.elibrary.entity.Category;

public interface CategoryService {

    public Category getById(Long id);

    public EditCategoryForm getFormById(Long id);

    public EditCategoryForm create(EditCategoryForm editCategoryForm) throws IOException;

    public void update(EditCategoryForm editCategoryForm) throws IOException;

    public void delete(Long id);

    public List<EditCategoryForm> getAll(Boolean forward, Long first, int max);
}
