package ru.kazachkov.springboot.security.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kazachkov.springboot.security.models.User;
import ru.kazachkov.springboot.security.services.UsersService;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserRestController {

    private final UsersService userService;

    public UserRestController(UsersService usersService) {
        this.userService = usersService;
    }

    @GetMapping
    public ResponseEntity<Authentication> getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(auth);
    }

    @GetMapping("/currentUser")
    public ResponseEntity<User> showUser(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}