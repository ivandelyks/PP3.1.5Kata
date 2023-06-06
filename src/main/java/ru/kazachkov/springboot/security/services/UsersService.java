package ru.kazachkov.springboot.security.services;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kazachkov.springboot.security.models.User;

import java.util.List;

public interface UsersService extends UserDetailsService {

    void saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByUsername(String username);

    void updateUser(User user);

    void deleteUser(Long id);

}
