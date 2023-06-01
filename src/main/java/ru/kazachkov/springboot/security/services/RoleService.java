package ru.kazachkov.springboot.security.services;


import ru.kazachkov.springboot.security.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRoles();

    void saveRole(Role roleAdmin);
}
