package ua.mk.par.elibrary.controller.user;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

import ua.mk.par.elibrary.controller.MainForm;
import ua.mk.par.elibrary.controller.user.forms.CreateUserForm;
import ua.mk.par.elibrary.controller.user.forms.EditUserForm;
import ua.mk.par.elibrary.controller.user.forms.LoginForm;
import ua.mk.par.elibrary.service.user.UserService;

@Controller
@RequestMapping(path = "/user")
public class userController {

    private final UserService userSerivice;

    public userController(UserService userSerivice) {
        this.userSerivice = userSerivice;
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public String getById(Long id,  Model model){
        EditUserForm editUserForm = userSerivice.getFormById(id);
        model.addAttribute("editUserForm",editUserForm);
        return "editUserForm";
    }

    @RequestMapping(path = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("createUserForm", new CreateUserForm());
        return "createUserForm";
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public String createByUser(ModelAndView modelAndView, @ModelAttribute("createUserForm") CreateUserForm createUserForm,
                                 BindingResult bindingResult, HttpServletRequest request) throws IOException {
        userSerivice.createByUser(createUserForm);
        return "login";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String create(ModelAndView modelAndView, @ModelAttribute("editUserForm") EditUserForm editUserForm,
                         BindingResult bindingResult, HttpServletRequest request) throws IOException {
        userSerivice.create(editUserForm);
        return "createUserForm";
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String update(@ModelAttribute("editUserForm") EditUserForm editUserForm, Model model) throws IOException {
        userSerivice.update(editUserForm);
        return getById(editUserForm.getId(), model);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String delete(Long id,  Model model) {
        userSerivice.delete(id);
        return "allUsersForm";
    }

    @RequestMapping(path = "/getmax", method = RequestMethod.GET)
    public @ResponseBody List<EditUserForm> getAll(Boolean forward, Long first, Integer max) throws IOException {
        List<EditUserForm> list = userSerivice.getAll(forward, first, max);
        return list;
    }

    @RequestMapping(path = "/getall", method = RequestMethod.GET)
    public String getAll_0(ModelAndView modelAndView, @ModelAttribute("allUserForm") EditUserForm editUserForm,
                         BindingResult bindingResult, HttpServletRequest request) throws IOException {
        return "allUsersForm";
    }

}
