package ua.mk.par.elibrary.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.mk.par.elibrary.controller.user.forms.CreateUserForm;
import ua.mk.par.elibrary.controller.user.forms.LoginForm;

@Controller
public class mainController {

    @RequestMapping(value = {"/","/mainform"}, method = RequestMethod.GET)
    public String viewHomeMain(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "mainform";
    }

    @RequestMapping("/adminpage")
    @Secured("ROLE_ADMIN")
    public String viewHomeMAdmin(Model model) {
        model.addAttribute("adminpage", new LoginForm());
        return "adminpage";
    }

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

}

