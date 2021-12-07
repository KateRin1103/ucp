package by.undrul.ucp.controller;

import by.undrul.ucp.dto.CargoDTO;
import by.undrul.ucp.dto.RouteDTO;
import by.undrul.ucp.exception.ServiceException;
import by.undrul.ucp.service.CargoService;
import by.undrul.ucp.service.CargoTypeService;
import by.undrul.ucp.service.CityService;
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
    private final CityService cityService;

    @Autowired
    public CargoController(CargoService cargoService, CargoTypeService cargoTypeService, CityService cityService) {
        this.cargoService = cargoService;
        this.cargoTypeService = cargoTypeService;
        this.cityService = cityService;
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


    @GetMapping("/add")
    public ModelAndView addCargo() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("types", cargoTypeService.findAll());
        modelAndView.addObject("cargoForm", new CargoDTO());
        modelAndView.setViewName("cargo/addCargo");

        return modelAndView;
    }


    @PostMapping("/add")
    public ModelAndView addCargo(Model model,
                           @Validated @ModelAttribute("cargoForm") CargoDTO dto,
                           BindingResult result) {

        ModelAndView modelAndView = new ModelAndView();

            modelAndView.addObject("cargo", dto);
            modelAndView.setViewName("route/chooseRoute");
        modelAndView.addObject("cities", cityService.findAll());
        modelAndView.addObject("routeForm", new RouteDTO());
        cargoService.save(dto);
        return modelAndView;


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
