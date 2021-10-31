package by.undrul.ucp.controller;

import by.undrul.ucp.dto.CompanyDTO;
import by.undrul.ucp.dto.RouteDTO;
import by.undrul.ucp.exception.ServiceException;
import by.undrul.ucp.service.CityService;
import by.undrul.ucp.service.RouteService;
import by.undrul.ucp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static by.undrul.ucp.controller.ControllerHelper.*;

@Controller
@RequestMapping("/routes")
public class RouteController {
    private final RouteService routeService;
    private final CityService cityService;
    private final UserService userService;


    @Autowired
    public RouteController(RouteService routeService,
                           CityService cityService,
                           UserService userService) {
        this.routeService = routeService;
        this.cityService = cityService;
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("route/routes");
        modelAndView.addObject("routes", routeService.findAll());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getDetails(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();

        RouteDTO dto = routeService.findById(id);
        modelAndView.addObject("route", dto);

        modelAndView.setViewName("/route/routeDetail");
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/add")
    public ModelAndView addRoute() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("cities", cityService.findAll());
        modelAndView.addObject("routeForm", new RouteDTO());
        modelAndView.setViewName("route/addRoute");

        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public String addRoute(Model model,
                           @Validated @ModelAttribute("routeForm") RouteDTO dto,
                           BindingResult result) {
        if (checkBindingResult(result)) {
            model.addAttribute("routeForm", dto);
            return "route/addRoute";
        }

        try {
            routeService.save(dto);
        } catch (ServiceException e) {
            model.addAttribute("message", e.getMessage());
            return goBackTo("route/addRoute");
        }

        return redirectTo("routes");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        routeService.delete(id);
        return redirectTo("routes");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();

        RouteDTO routeDTO = routeService.findById(id);
        modelAndView.addObject("cities", cityService.findAll());
        modelAndView.addObject("routeForm", routeDTO);
        modelAndView.setViewName("/route/updateRoute");

        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @Validated @ModelAttribute("routeForm") RouteDTO routeDTO,
                         BindingResult bindingResult) {
        if (checkBindingResult(bindingResult)) {
            return goBackTo("/route/updateRoute");
        }
        routeDTO.setId(id);
        routeService.update(routeDTO);

        return redirectTo("routes");
    }


}
