package ua.mk.par.elibrary.controller.role;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;
import ua.mk.par.elibrary.controller.role.forms.EditRoleForm;
import ua.mk.par.elibrary.service.role.RoleSerivice;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "/role")
public class roleController {

    private final RoleSerivice roleSerivice;

    public roleController(RoleSerivice roleSerivice) {
        this.roleSerivice = roleSerivice;
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public String getById(Long id,  Model model) {
        EditRoleForm editRoleForm = roleSerivice.getFormById(id);
        model.addAttribute("editRoleForm", editRoleForm);
        return "editRoleForm";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String create(ModelAndView modelAndView, @ModelAttribute("editRoleForm") EditRoleForm editRoleForm,
                         BindingResult bindingResult, HttpServletRequest request) throws IOException {
        roleSerivice.create(editRoleForm);
        return "editRoleForm";
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String update(ModelAndView modelAndView, @ModelAttribute("editRoleForm") EditRoleForm editRoleForm,
                         BindingResult bindingResult, HttpServletRequest request) throws IOException {
        roleSerivice.update(editRoleForm);
        return "editRoleForm";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String delete(Long id,  Model model) {
        roleSerivice.delete(id);
        return "allRolesForm";
    }

    @RequestMapping(path = "/getmax", method = RequestMethod.GET)
    public @ResponseBody
    List<EditRoleForm> getAll(Boolean forward, Long first, Integer max) throws IOException {
        List<EditRoleForm> list = roleSerivice.getAll(forward, first, max);
        return list;
    }

    @RequestMapping(path = "/getall", method = RequestMethod.GET)
    public String getAll_0(ModelAndView modelAndView, @ModelAttribute("allRolesForm") EditRoleForm EditRoleForm,
                           BindingResult bindingResult, HttpServletRequest request) throws IOException {
        return "allRolesForm";
    }
}
