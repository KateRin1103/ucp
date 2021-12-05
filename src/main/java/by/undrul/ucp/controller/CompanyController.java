package by.undrul.ucp.controller;

import by.undrul.ucp.dto.CompanyDTO;
import by.undrul.ucp.dto.RouteDTO;
import by.undrul.ucp.dto.UserDTO;
import by.undrul.ucp.exception.ServiceException;
import by.undrul.ucp.service.CompanyService;
import by.undrul.ucp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

import static by.undrul.ucp.controller.ControllerHelper.*;
import static by.undrul.ucp.controller.ControllerHelper.redirectTo;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final UserService userService;

    @Autowired
    public CompanyController(CompanyService companyService,
                             UserService userService){
        this.companyService=companyService;
        this.userService=userService;
    }

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("company/companies");
        modelAndView.addObject("companies", companyService.findAll());

        return modelAndView;
    }

    @GetMapping("/myCompany")
    public ModelAndView myCompany(Principal principal) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("company/companyDetail");

        UserDTO userDTO = userService.findByUsername(principal.getName());
        CompanyDTO myCompany = companyService.findByUser(userDTO);

        if(myCompany!=null) {
            modelAndView.addObject("company", myCompany);
        }

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getDetails(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();

        CompanyDTO dto = companyService.findById(id);
        modelAndView.addObject("company", dto);

        modelAndView.setViewName("/company/companyDetail");
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_CARRIER')")
    @GetMapping("/add")
    public ModelAndView addCompany() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("companyForm", new CompanyDTO());
        modelAndView.setViewName("company/addCompany");

        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_CARRIER')")
    @PostMapping("/add")
    public String addCompany(Model model,
                           @Validated @ModelAttribute("companyForm") CompanyDTO dto,
                           BindingResult result,
                             Principal principal) {
        if (checkBindingResult(result)) {
            model.addAttribute("companyForm", dto);
            return "company/addCompany";
        }

        UserDTO user = userService.findByUsername(principal.getName());
        try {
            dto.setUser(user);
            companyService.save(dto);
        } catch (ServiceException e) {
            model.addAttribute("message", e.getMessage());
            return goBackTo("company/addCompany");
        }

        return redirectTo("companies");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        companyService.delete(id);
        return redirectTo("companies");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();

        CompanyDTO companyDTO = companyService.findById(id);
        modelAndView.addObject("companyForm", companyDTO);
        modelAndView.setViewName("/company/updateCompany");

        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @Validated @ModelAttribute("companyForm") CompanyDTO companyDTO,
                         BindingResult bindingResult) {
        if (checkBindingResult(bindingResult)) {
            return goBackTo("/company/updateCompany");
        }
        companyDTO.setId(id);
        companyService.update(companyDTO);

        return redirectTo("companies");
    }

}
