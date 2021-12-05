package by.undrul.ucp.controller;

import by.undrul.ucp.dto.CargoDTO;
import by.undrul.ucp.dto.OrderDTO;
import by.undrul.ucp.dto.RouteDTO;
import by.undrul.ucp.exception.ServiceException;
import by.undrul.ucp.service.*;
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
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final CargoService cargoService;
    private final CompanyService companyService;
    private final DeliveryMethodService deliveryMethodService;
    private final UserService userService;
    private final RouteService routeService;
    private final CargoTypeService cargoTypeService;

    @Autowired
    public OrderController(OrderService orderService,
                           CargoService cargoService,
                           CompanyService companyService,
                           DeliveryMethodService deliveryMethodService,
                           UserService userService,
                           RouteService routeService,
                           CargoTypeService cargoTypeService) {

        this.orderService = orderService;
        this.cargoService = cargoService;
        this.companyService = companyService;
        this.deliveryMethodService = deliveryMethodService;
        this.userService = userService;
        this.routeService = routeService;
        this.cargoTypeService=cargoTypeService;
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/add")
    public ModelAndView addOrder() {
        ModelAndView modelAndView = new ModelAndView();


        modelAndView.addObject("types", cargoTypeService.findAll());
        modelAndView.addObject("companies", companyService.findAll());
        modelAndView.addObject("routes", routeService.findAll());
        modelAndView.addObject("deliveryMethods", deliveryMethodService.findAll());
        modelAndView.addObject("cargoForm", new CargoDTO());
        modelAndView.addObject("routeForm", new RouteDTO());
        modelAndView.addObject("orderForm", new OrderDTO());
        modelAndView.setViewName("order/addOrder");

        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
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
    }

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
