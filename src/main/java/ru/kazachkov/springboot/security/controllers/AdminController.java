package ru.kazachkov.springboot.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kazachkov.springboot.security.models.User;
import ru.kazachkov.springboot.security.services.RoleService;
import ru.kazachkov.springboot.security.services.UsersService;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    private final UsersService usersService;

    @Autowired
    public AdminController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> showAllUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        return ResponseEntity.ok(usersService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        usersService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user) {
        usersService.updateUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws Exception {
        User user = usersService.getUserById(id);
        if(user == null) {
            throw new Exception("There is no user with id = " + id + " in database.");
        }

        usersService.deleteUser(id);
        return ResponseEntity.ok("User with id = " + id + " was deleted.");
    }
}