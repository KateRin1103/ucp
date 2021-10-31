package by.undrul.ucp.controller;

import by.undrul.ucp.dto.CargoDTO;
import by.undrul.ucp.exception.ServiceException;
import by.undrul.ucp.service.CargoService;
import by.undrul.ucp.service.CargoTypeService;
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
@RequestMapping("/cargos")
public class CargoController {
    private final CargoService cargoService;
    private final CargoTypeService cargoTypeService;

    @Autowired
    public CargoController(CargoService cargoService,
                           CargoTypeService cargoTypeService) {
        this.cargoService = cargoService;
        this.cargoTypeService = cargoTypeService;
    }

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("cargo/cargos");
        modelAndView.addObject("cargos", cargoService.findAll());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getDetails(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();

        CargoDTO dto = cargoService.findById(id);
        modelAndView.addObject("cargo", dto);

        modelAndView.setViewName("/cargo/cargoDetail");
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/add")
    public ModelAndView addCargo() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("types", cargoTypeService.findAll());
        modelAndView.addObject("cargoForm", new CargoDTO());
        modelAndView.setViewName("cargo/addCargo");

        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public String addCargo(Model model,
                           @Validated @ModelAttribute("cargoForm") CargoDTO dto,
                           BindingResult result) {
        if (checkBindingResult(result)) {
            model.addAttribute("cargoForm", dto);
            return "cargo/addCargo";
        }

        try {
            cargoService.save(dto);
        } catch (ServiceException e) {
            model.addAttribute("message", e.getMessage());
            return goBackTo("cargo/addCargo");
        }

        return redirectTo("cargos");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        cargoService.delete(id);
        return redirectTo("cargos");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();

        CargoDTO cargoDTO = cargoService.findById(id);

        modelAndView.addObject("types", cargoTypeService.findAll());
        modelAndView.addObject("cargoForm", cargoDTO);
        modelAndView.setViewName("/cargo/updateCargo");

        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @Validated @ModelAttribute("cargoForm") CargoDTO cargoDTO,
                         BindingResult bindingResult) {
        if (checkBindingResult(bindingResult)) {
            return goBackTo("/cargo/updateCargo");
        }
        cargoDTO.setId(id);
        cargoService.update(cargoDTO);

        return redirectTo("cargos");
    }

}
