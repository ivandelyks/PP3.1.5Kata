package ru.kazachkov.springboot.security.services;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kazachkov.springboot.security.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    void add(User user);

    void update(User user);

    User getById(Long id);

    User findByUserName(String username);

    User getUserByUsername(String username);

    void delete(Long id);

}
