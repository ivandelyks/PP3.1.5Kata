package ru.kazachkov.springboot.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kazachkov.springboot.security.models.User;
import ru.kazachkov.springboot.security.services.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUser(Model model, Principal principal) {
        User user = userService.findByUserName(principal.getName());
        model.addAttribute("user", userService.getById(user.getId()));
        return "user";
    }
}
