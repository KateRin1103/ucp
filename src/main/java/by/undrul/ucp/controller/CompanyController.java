package by.undrul.ucp.controller;

import by.undrul.ucp.dto.CompanyDTO;
import by.undrul.ucp.dto.RouteDTO;
import by.undrul.ucp.exception.ServiceException;
import by.undrul.ucp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static by.undrul.ucp.controller.ControllerHelper.*;
import static by.undrul.ucp.controller.ControllerHelper.redirectTo;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService){
        this.companyService=companyService;
    }

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("company/companies");
        modelAndView.addObject("companies", companyService.findAll());

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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/add")
    public ModelAndView addRoute() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("companyForm", new CompanyDTO());
        modelAndView.setViewName("company/addCompany");

        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public String addRoute(Model model,
                           @Validated @ModelAttribute("companyForm") CompanyDTO dto,
                           BindingResult result) {
        if (checkBindingResult(result)) {
            model.addAttribute("companyForm", dto);
            return "company/addCompany";
        }

        try {
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

        CompanyDTO CompanyDTO = companyService.findById(id);
        modelAndView.addObject("companyForm", CompanyDTO);
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
