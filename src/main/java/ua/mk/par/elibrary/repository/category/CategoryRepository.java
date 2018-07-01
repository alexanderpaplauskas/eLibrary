package ua.mk.par.elibrary.repository.category;

import java.util.List;

import ua.mk.par.elibrary.entity.Category;

public interface CategoryRepository {

    public Category getById(Long id);

    public List<Category> getAll(Boolean forward, Long first, Integer max);

    public Category create(Category category);

    public Category update(Category category);

    public void delete(Long id);
}
