package by.undrul.ucp.controller;

import by.undrul.ucp.dto.RoleDTO;
import by.undrul.ucp.dto.UserDTO;
import by.undrul.ucp.exception.ServiceException;
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
import java.util.Objects;

import static by.undrul.ucp.controller.ControllerHelper.*;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_CUSTOMER')")
    @GetMapping("/personal-cabinet")
    public String getPersonalCabinet(Model model,
                                     Principal principal) {

        UserDTO userDto = userService.findByUsername(principal.getName());

        model.addAttribute("user", userDto);
        return "/user/userPersonalCabinet";
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping("/update-user")
    public String updateUser(@Validated @ModelAttribute("user") UserDTO userDto,
                             Principal principal,
                             BindingResult bindingResult,
                             Model model) {

        if (checkBindingResult(bindingResult)) {
            return goBackTo("user/userPersonalCabinet");
        }
        try {
            userDto.setUsername(principal.getName());
            userService.update(userDto);
        } catch (ServiceException ex) {
            model.addAttribute("error", ex.getMessage());
            return goBackTo("user/userPersonalCabinet");
        }
        return redirectTo("personal-cabinet");
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public ModelAndView findAllUsers() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("user/users");
        modelAndView.addObject("users", userService.findAll());

        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users/{id}")
    public String blockUser(@PathVariable(name = "id") Long id,
                            @RequestParam(required = false) String block,
                            @RequestParam(required = false) String seller,
                            @RequestParam(required = false) String admin) {

        UserDTO userDTO = userService.findById(id);
        if (Objects.nonNull(block)) {
            if (userDTO.getBlocked() == 0) {
                userDTO.setBlocked(1);
            }
            else userDTO.setBlocked(0);
        }
        if (Objects.nonNull(seller)) {
            RoleDTO roleDto = new RoleDTO();
            roleDto.setName("ROLE_CARREER");
            roleDto.setId(3L);
            if (userDTO.getRoles().contains(roleDto)) {
                userDTO.getRoles().remove(roleDto);
            }
            else userDTO.getRoles().add(roleDto);
        }
        if (Objects.nonNull(admin)) {
            RoleDTO roleDto = new RoleDTO();
            roleDto.setName("ROLE_ADMIN");
            roleDto.setId(2L);
            if (userDTO.getRoles().contains(roleDto)) {
                userDTO.getRoles().remove(roleDto);
            }
            else userDTO.getRoles().add(roleDto);
        }
        userService.update(userDTO);
        return redirectTo("users");
    }


}