package ua.mk.par.elibrary.controller.publisher;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import ua.mk.par.elibrary.controller.publisher.forms.EditPublisherForm;
import ua.mk.par.elibrary.service.publisher.PublisherService;

@Controller
@RequestMapping(path = "/publisher")
public class publisherController {

    private final PublisherService publisherSerivice;

    public publisherController(PublisherService publisherSerivice) {
        this.publisherSerivice = publisherSerivice;
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public String getById(Long id,  Model model) {
        EditPublisherForm editPublisherForm = publisherSerivice.getFormById(id);
        model.addAttribute("editPublisherForm", editPublisherForm);
        return "editPublisherForm";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String create(ModelAndView modelAndView, @ModelAttribute("editPublisherForm") EditPublisherForm editPublisherForm,
                         BindingResult bindingResult, HttpServletRequest request) throws IOException {
        publisherSerivice.create(editPublisherForm);
        return "allPublishersForm";
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String update(ModelAndView modelAndView, @ModelAttribute("editPublisherForm") EditPublisherForm editPublisherForm,
                         BindingResult bindingResult, HttpServletRequest request) throws IOException {
        publisherSerivice.update(editPublisherForm);
        return "editPublisherForm";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String delete(Long id,  Model model) {
        publisherSerivice.delete(id);
        return "allPublishersForm";
    }

    @RequestMapping(path = "/getmax", method = RequestMethod.GET)
    public @ResponseBody
    List<EditPublisherForm> getAll(Boolean forward, Long first, Integer max) throws IOException {
        List<EditPublisherForm> list = publisherSerivice.getAll(forward, first, max);
        return list;
    }

    @RequestMapping(path = "/getall", method = RequestMethod.GET)
    public String getAll_0(ModelAndView modelAndView, @ModelAttribute("allPublishersForm") EditPublisherForm EditPublisherForm,
                           BindingResult bindingResult, HttpServletRequest request) throws IOException {
        return "allPublishersForm";
    }

}
