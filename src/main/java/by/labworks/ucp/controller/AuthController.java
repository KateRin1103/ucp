package by.labworks.ucp.controller;

import by.labworks.ucp.dto.SignUpRequest;
import by.labworks.ucp.exception.ServiceException;
import by.labworks.ucp.service.SecurityService;
import by.labworks.ucp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

import static by.labworks.ucp.controller.ControllerHelper.*;

@Controller
public class AuthController {

    private final UserService userService;
    private final SecurityService securityService;

    @Autowired
    public AuthController(UserService userService,
                          SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }


    @GetMapping(value = "/accessDenied")
    public ModelAndView accessDeniedRedirect() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "Вы не можете просматривать данный контент.");
        modelAndView.setViewName("util/error/accessDenied");
        return modelAndView;
    }


    @GetMapping("/registration")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("common/registration");
        modelAndView.addObject("userForm", new SignUpRequest());
        return modelAndView;
    }

    @PostMapping("/registration")
    public String registration(Model model,
                               @Validated @ModelAttribute("userForm") SignUpRequest userForm,
                               BindingResult bindingResult) {

        if (checkBindingResult(bindingResult)) {
            return goBackTo("common/registration");
        }
        try {
            userService.signUp(userForm);
        } catch (ServiceException ex) {
            model.addAttribute("error", ex.getMessage());
            return goBackTo("common/registration");
        }
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
        // return "common/welcome";
        return redirectTo("personal-cabinet");
    }


    @GetMapping({"/login"})
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout,
                              @RequestParam(value = "accessDenied", required = false) String accessDenied) {
        ModelAndView modelAndView = new ModelAndView();

        if (Objects.nonNull(error)) {
            modelAndView.addObject("error", "Данные были введены неверно.");
        }
        if (Objects.nonNull(logout)) {
            modelAndView.addObject("message", "Вы вышли из системы успешно.");
        }
        if (Objects.nonNull(accessDenied)) {
            modelAndView.addObject("message", "Вы не можете просматривать данный контент.");
        }
        modelAndView.setViewName("common/login");

        return modelAndView;
    }

    @GetMapping({"/"})
    public String welcome() {
        return "common/welcome";
    }


}
