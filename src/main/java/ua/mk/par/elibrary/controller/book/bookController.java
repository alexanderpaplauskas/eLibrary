package ua.mk.par.elibrary.controller.book;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;
import ua.mk.par.elibrary.controller.book.forms.EditBookForm;
import ua.mk.par.elibrary.service.book.BookService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "/book")
public class bookController {

    private final BookService bookSerivice;

    public bookController(BookService bookSerivice) {
        this.bookSerivice = bookSerivice;
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public String getById(Long id,  Model model) {
        EditBookForm editBookForm = bookSerivice.getFormById(id);
        model.addAttribute("editBookForm", editBookForm);
        return "editBookForm";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String create(ModelAndView modelAndView, @ModelAttribute("editBookForm") EditBookForm editBookForm,
                         BindingResult bindingResult, HttpServletRequest request) throws IOException {
        bookSerivice.create(editBookForm);
        return "editBookForm";
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String update(ModelAndView modelAndView, @ModelAttribute("editBookForm") EditBookForm editBookForm, Model model) throws IOException {
        bookSerivice.update(editBookForm);
        return getById(editBookForm.getId(), model);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String delete(Long id,  Model model) {
        bookSerivice.delete(id);
        return "allBooksForm";
    }

    @RequestMapping(path = "/getmax", method = RequestMethod.GET)
    public @ResponseBody
    List<EditBookForm> getAll(Boolean forward, Long first, Integer max) throws IOException {
        List<EditBookForm> list = bookSerivice.getAll(forward, first, max);
        return list;
    }

    @RequestMapping(path = "/getall", method = RequestMethod.GET)
    public String getAll_0(ModelAndView modelAndView, @ModelAttribute("allBooksForm") EditBookForm editBookForm,
                           BindingResult bindingResult, HttpServletRequest request) throws IOException {
        return "allBooksForm";
    }

}
