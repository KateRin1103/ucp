package by.labworks.ucp.controller;

import by.labworks.ucp.dto.*;
import by.labworks.ucp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import static by.labworks.ucp.controller.ControllerHelper.*;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final CargoService cargoService;
    private final CompanyService companyService;
    private final DeliveryMethodService deliveryMethodService;
    private final UserService userService;
    private final CityService cityService;
    private final RouteService routeService;
    private final CargoTypeService cargoTypeService;

    @Autowired
    public OrderController(OrderService orderService,
                           CargoService cargoService,
                           CompanyService companyService,
                           DeliveryMethodService deliveryMethodService,
                           UserService userService,
                           RouteService routeService,
                           CargoTypeService cargoTypeService,
                           CityService cityService) {

        this.orderService = orderService;
        this.cargoService = cargoService;
        this.companyService = companyService;
        this.deliveryMethodService = deliveryMethodService;
        this.userService = userService;
        this.routeService = routeService;
        this.cargoTypeService = cargoTypeService;
        this.cityService = cityService;
    }


    @GetMapping
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("order/orders");
        modelAndView.addObject("orders", orderService.findAll());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getDetails(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();

        OrderDTO dto = orderService.findById(id);
        modelAndView.addObject("order", dto);

        modelAndView.setViewName("/order/orderDetail");
        return modelAndView;
    }


    @PostMapping("/add")
    public String addOrder(Model model, Principal principal,
                           @RequestParam(name = "distance") String distance,
                           @RequestParam(name = "cargo") String cargo,
                           @ModelAttribute(name = "cityA") String a,
                           @ModelAttribute(name = "cityB") String b) {

        OrderDTO orderDTO = new OrderDTO();
        UserDTO user = userService.findByUsername(principal.getName());
        CompanyDTO companyDTO = companyService.findAll().get(0);
        CargoDTO cargoDTO = cargoService.findById(Long.parseLong(cargo));
        Double distanceKM = Double.parseDouble(distance);
        Double cost = companyDTO.getPrice_km() * distanceKM + companyDTO.getPrice_kg() * cargoDTO.getWeight();
        orderDTO.setCustomer(user);
        orderDTO.setCityA(cityService.findById(Long.parseLong(a)));
        orderDTO.setCityB(cityService.findById(Long.parseLong(b)));
        orderDTO.setCompany(companyDTO);
        orderDTO.setCargo(cargoDTO);
        orderDTO.setCreateDate(LocalDate.now());
        orderDTO.setCost(cost);

        orderService.save(orderDTO);
        return redirectTo("");
    }


    /*@PostMapping("/add")
    public String addRoute(Model model,
                           @Validated @ModelAttribute("orderForm") OrderDTO dto,
                           BindingResult result) {
        if (checkBindingResult(result)) {
            model.addAttribute("orderForm", dto);
            return "order/addOrder";
        }

        try {
            orderService.save(dto);
        } catch (ServiceException e) {
            model.addAttribute("message", e.getMessage());
            return goBackTo("order/addOrder");
        }

        return redirectTo("orders");
    }*/

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        orderService.delete(id);
        return redirectTo("orders");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();

        OrderDTO orderDTO = orderService.findById(id);
        modelAndView.addObject("companies", companyService.findAll());
        modelAndView.addObject("routes", routeService.findAll());
        modelAndView.addObject("deliveryMethods", deliveryMethodService.findAll());
        modelAndView.addObject("orderForm", orderDTO);
        modelAndView.setViewName("/order/updateOrder");

        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @Validated @ModelAttribute("orderForm") OrderDTO orderDTO,
                         BindingResult bindingResult) {
        if (checkBindingResult(bindingResult)) {
            return goBackTo("/order/updateOrder");
        }
        orderDTO.setId(id);
        orderService.update(orderDTO);

        return redirectTo("orders");
    }

}
