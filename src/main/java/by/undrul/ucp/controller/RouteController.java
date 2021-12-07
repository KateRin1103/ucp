package by.undrul.ucp.controller;

import by.undrul.ucp.dto.CityDTO;
import by.undrul.ucp.dto.CompanyDTO;
import by.undrul.ucp.dto.RouteDTO;
import by.undrul.ucp.dto.UserDTO;
import by.undrul.ucp.exception.ServiceException;
import by.undrul.ucp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import static by.undrul.ucp.controller.ControllerHelper.*;

@Controller
@RequestMapping("/routes")
public class RouteController {
    private final RouteService routeService;
    private final CityService cityService;
    private final UserService userService;
    private final DeliveryMethodService deliveryMethodService;
    private final CompanyService companyService;

    @Autowired
    public RouteController(RouteService routeService,
                           CityService cityService, UserService userService, DeliveryMethodService deliveryMethodService, CompanyService companyService) {
        this.routeService = routeService;
        this.cityService = cityService;
        this.userService = userService;
        this.deliveryMethodService = deliveryMethodService;
        this.companyService = companyService;
    }

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("route/routes");
        List<RouteDTO> all = routeService.findAll();
        modelAndView.addObject("routes", all);

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

    @PreAuthorize("hasRole('ROLE_CARRIER')")
    @GetMapping("/add")
    public ModelAndView addRoute() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("cities", cityService.findAll());
        modelAndView.addObject("methods", deliveryMethodService.findAll());
        modelAndView.addObject("routeForm", new RouteDTO());
        modelAndView.setViewName("route/addRoute");

        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_CARRIER')")
    @PostMapping("/add")
    public String addRoute(Model model,
                           @Validated @ModelAttribute("routeForm") RouteDTO dto,
                           BindingResult result,
                           Principal principal) {
        if (checkBindingResult(result)) {
            model.addAttribute("routeForm", dto);
            return "route/addRoute";
        }

        try {
            UserDTO userDTO = userService.findByUsername(principal.getName());
            CompanyDTO companyDTO = companyService.findByUser(userDTO);
            companyService.addRoute(companyDTO.getId(),dto);
            RouteDTO route = new RouteDTO();
            route.setDistance(dto.getDistance());
            route.setCityA(dto.getCityB());
            route.setCityB(dto.getCityA());
            route.setName(dto.getName());
            route.setDeliveryMethod(dto.getDeliveryMethod());
            companyService.addRoute(companyDTO.getId(), route);
//            routeService.save(dto);
        } catch (ServiceException e) {
            model.addAttribute("message", e.getMessage());
            return goBackTo("route/addRoute");
        }

        return redirectTo("companies/myCompany");
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





    @PreAuthorize("hasRole('ROLE_CARRIER')")
    @GetMapping("/cities/add")
    public ModelAndView addCity() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cityForm", new CityDTO());
        modelAndView.setViewName("city/addCity");

        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_CARRIER')")
    @PostMapping("/cities/add")
    public String addCity(Model model,
                           @Validated @ModelAttribute("cityForm") CityDTO dto,
                           BindingResult result,
                           Principal principal) {
        if (checkBindingResult(result)) {
            model.addAttribute("cityForm", dto);
            return "city/addCity";
        }

        try {

            cityService.save(dto);
        } catch (ServiceException e) {
            model.addAttribute("message", e.getMessage());
            return goBackTo("city/addCity");
        }

        return redirectTo("routes/add");
    }


    @GetMapping("/choose")
    public ModelAndView chooseRoute(Model model) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("cargo",model.getAttribute("cargo"));
        modelAndView.addObject("cities", cityService.findAll());
        modelAndView.addObject("routeForm", new RouteDTO());
        modelAndView.setViewName("route/chooseRoute");

        return modelAndView;
    }


    @PostMapping("/choose")
    public ModelAndView chooseRoute(Model model,
                           @Validated @ModelAttribute("routeForm") RouteDTO dto,
                           BindingResult result,
                           Principal principal) {


        Map<CompanyDTO,Map<List<CityDTO>,Double>> routes = routeService.findRoute(dto.getCityA(), dto.getCityB());



        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("routes", routes);
        modelAndView.addObject("cargo", model.getAttribute("cargo"));
        modelAndView.setViewName("route/resultRoutes");

        return modelAndView;
    }

}
