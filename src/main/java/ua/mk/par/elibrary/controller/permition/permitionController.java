package ua.mk.par.elibrary.controller.permition;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;
import ua.mk.par.elibrary.controller.permition.forms.EditPermitionForm;
import ua.mk.par.elibrary.service.permition.PermitionService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "/permition")
public class permitionController {

    private final PermitionService permitionSerivice;

    public permitionController(PermitionService permitionSerivice) {
        this.permitionSerivice = permitionSerivice;
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public String getById(Long id, Model model) {
        EditPermitionForm editPermitionForm = permitionSerivice.getFormById(id);
        model.addAttribute("editPermitionForm", editPermitionForm);
        return "editPermitionForm";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String create(ModelAndView modelAndView, @ModelAttribute("editPermitionForm") EditPermitionForm editPermitionForm,
                         BindingResult bindingResult, HttpServletRequest request) throws IOException {
        permitionSerivice.create(editPermitionForm);
        return "editPermitionForm";
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String update(ModelAndView modelAndView, @ModelAttribute("editPermitionForm") EditPermitionForm editPermitionForm,
                         BindingResult bindingResult, HttpServletRequest request) throws IOException {
        permitionSerivice.update(editPermitionForm);
        return "editPermitionForm";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String delete(Long id, Model model) {
        permitionSerivice.delete(id);
        return "allPermitionsForm";
    }

    @RequestMapping(path = "/getmax", method = RequestMethod.GET)
    public @ResponseBody
    List<EditPermitionForm> getAll(Boolean forward, Long first, Integer max) throws IOException {
        List<EditPermitionForm> list = permitionSerivice.getAll(forward, first, max);
        return list;
    }

    @RequestMapping(path = "/getall", method = RequestMethod.GET)
    public String getAll_0(ModelAndView modelAndView, @ModelAttribute("allPermitionsForm") EditPermitionForm editPermitionForm,
                           BindingResult bindingResult, HttpServletRequest request) throws IOException {
        return "allPermitionsForm";
    }

}
