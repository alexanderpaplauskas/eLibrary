package ua.mk.par.elibrary.controller.author;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;
import ua.mk.par.elibrary.controller.author.forms.EditAuthorForm;
import ua.mk.par.elibrary.service.author.AuthorService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "/author")
public class authorController {

    private final AuthorService authorSerivice;

    public authorController(AuthorService authorSerivice) {
        this.authorSerivice = authorSerivice;
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)  //http://localhost:8080/author/get/1
    public String getById(Long id,  Model model) {
        EditAuthorForm editAuthorForm = authorSerivice.getFormById(id);
        model.addAttribute("editAuthorForm", editAuthorForm);
        return "editAuthorForm";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String create(ModelAndView modelAndView, @ModelAttribute("editAuthorForm") EditAuthorForm editAuthorForm,
                         BindingResult bindingResult, HttpServletRequest request) throws IOException {
        authorSerivice.create(editAuthorForm);
        return "editAuthorForm";
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String update(ModelAndView modelAndView, @ModelAttribute("editAuthorForm") EditAuthorForm editAuthorForm,
                         BindingResult bindingResult, HttpServletRequest request) throws IOException {
        authorSerivice.update(editAuthorForm);
        return "editAuthorForm";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String delete(Long id,  Model model) {
        authorSerivice.delete(id);
        return "allAuthorsForm";
    }

    @RequestMapping(path = "/getmax", method = RequestMethod.GET)
    public @ResponseBody
    List<EditAuthorForm> getAll(Boolean forward, Long first, Integer max) throws IOException {
        List<EditAuthorForm> list = authorSerivice.getAll(forward, first, max);
        return list;
    }

    @RequestMapping(path = "/getall", method = RequestMethod.GET)
    public String getAll_0(ModelAndView modelAndView, @ModelAttribute("createUserForm") EditAuthorForm editAuthorForm,
                BindingResult bindingResult, HttpServletRequest request) throws IOException {
            return "allAuthorsForm";
    }

}
