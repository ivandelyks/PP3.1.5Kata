package ru.kazachkov.springboot.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kazachkov.springboot.security.models.User;
import ru.kazachkov.springboot.security.services.UsersService;

import java.security.Principal;

@Controller
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user")
    public String getUserPage(Model model, Principal principal) {
        model.addAttribute("authUser", usersService.getUserByUsername(principal.getName()));
        model.addAttribute("authRoles", usersService.getUserByUsername(principal.getName()).getRoles());
        return "user";
    }
}
