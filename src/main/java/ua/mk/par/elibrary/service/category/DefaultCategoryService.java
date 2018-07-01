package ua.mk.par.elibrary.service.category;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ua.mk.par.elibrary.controller.category.forms.EditCategoryForm;
import ua.mk.par.elibrary.entity.Category;
import ua.mk.par.elibrary.entity.Permition;
import ua.mk.par.elibrary.repository.category.CategoryRepository;
import ua.mk.par.elibrary.repository.permition.PermitionRepository;

@Service
@Transactional
public class DefaultCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final PermitionRepository permitionRepository;

    public DefaultCategoryService(CategoryRepository categoryRepository, PermitionRepository permitionRepository) {
        this.categoryRepository = categoryRepository;
        this.permitionRepository = permitionRepository;
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.getById(id);
    }

    @Override
    public EditCategoryForm getFormById(Long id) {
        if (id == 0l){
            EditCategoryForm eu = new EditCategoryForm();
            return eu;
        }
        Category cat = getById(id);
        if (cat != null) {
            EditCategoryForm ecat = new EditCategoryForm();
            ecat.setId(cat.getId());
            ecat.setName(cat.getName());
            List<String> formPerms = new ArrayList<>();
            for (Permition permition : cat.getPermitions()){
                formPerms.add("{\"id\":\""+permition.getId()+"\",\"name\":\""+permition.getName()+"\"}");
            }
            ecat.setPermitions(formPerms);
            ecat.setDescription(cat.getDescription());
            return ecat;
        }
        return null;
    }

    @Override
    public List<EditCategoryForm> getAll(Boolean forward, Long first, int max) {
        List<Category> categories = categoryRepository.getAll(forward, first, max);
        List<EditCategoryForm> formCategories = new ArrayList<>();
        for (Category category : categories) {
            EditCategoryForm formCat = new EditCategoryForm();
            formCat.setId(category.getId());
            formCat.setName(category.getName());
            List<String> formPerms = new ArrayList<>();
            for (Permition permition : category.getPermitions()){
                formPerms.add(permition.getName());
            }
            formCat.setPermitions(formPerms);
            formCat.setDescription(category.getDescription());
            formCategories.add(formCat);
        }
        if (!forward) {
            Collections.sort(formCategories, new Comparator<EditCategoryForm>() {
                @Override
                public int compare(EditCategoryForm o1, EditCategoryForm o2) {
                    return o1.getId().compareTo(o2.getId());
                }
            });
        }
        return formCategories;
    }

    @Override
    public EditCategoryForm create(EditCategoryForm editCategoryForm) throws IOException {
        Category category = new Category();
        category.setName(editCategoryForm.getName());
        category.setDescription(editCategoryForm.getDescription());
        categoryRepository.create(category);
        editCategoryForm.setId(category.getId());
        if (category.getId()==null) { return editCategoryForm;
        }
        if (editCategoryForm.getPermitions() != null) {
            update(editCategoryForm);
        }
        return editCategoryForm;
    }

    @Override
    public void update(EditCategoryForm editCategoryForm) throws IOException  {
        if (editCategoryForm.getId()==null){
            create(editCategoryForm);
            return;
        }
        Category category = categoryRepository.getById(editCategoryForm.getId());
        if (category == null) {
            return;
        } else {
            category.setId(editCategoryForm.getId());
            category.setName(editCategoryForm.getName());
            List<Permition> list_perm = new ArrayList<>();
            for (String perm : editCategoryForm.getPermitions()){
                if (!perm.isEmpty()){
                Long cur_id = Long.parseLong(perm, 10);
                list_perm.add(permitionRepository.getById(cur_id));
            }}
            category.setPermitions(list_perm);
            category.setDescription(editCategoryForm.getDescription());
            categoryRepository.update(category);
        }
    }

    @Override
    public void delete(Long id) {
        categoryRepository.delete(id);

    }
}
