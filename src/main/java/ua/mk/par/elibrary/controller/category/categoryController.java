package ua.mk.par.elibrary.controller.category;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

import ua.mk.par.elibrary.controller.category.forms.EditCategoryForm;
import ua.mk.par.elibrary.service.category.CategoryService;

@Controller
@RequestMapping(path = "/category")
public class categoryController {

    private final CategoryService categorySerivice;

    public categoryController(CategoryService categorySerivice) {
        this.categorySerivice = categorySerivice;
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public String getById(Long id,  Model model) {
        EditCategoryForm editCategoryForm = categorySerivice.getFormById(id);
        model.addAttribute("editCategoryForm", editCategoryForm);
        return "editCategoryForm";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String create(ModelAndView modelAndView, @ModelAttribute("editCategoryForm") EditCategoryForm editCategoryForm,
                         BindingResult bindingResult, HttpServletRequest request) throws IOException {
        categorySerivice.create(editCategoryForm);
        return "editCategoryForm";
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String update(@ModelAttribute("editCategoryForm") EditCategoryForm editCategoryForm, Model model) throws IOException {
        categorySerivice.update(editCategoryForm);
        return getById(editCategoryForm.getId(), model);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String delete(Long id,  Model model) {
        categorySerivice.delete(id);
        return "allCategoriesForm";
    }

    @RequestMapping(path = "/getmax", method = RequestMethod.GET)
    public @ResponseBody
    List<EditCategoryForm> getAll(Boolean forward, Long first, Integer max) throws IOException {
        List<EditCategoryForm> list = categorySerivice.getAll(forward, first, max);
        return list;
    }

    @RequestMapping(path = "/getall", method = RequestMethod.GET)
    public String getAll_0(ModelAndView modelAndView, @ModelAttribute("allCategoriesForm") EditCategoryForm editCategoryForm,
                           BindingResult bindingResult, HttpServletRequest request) throws IOException {
        return "allCategoriesForm";
    }

}
