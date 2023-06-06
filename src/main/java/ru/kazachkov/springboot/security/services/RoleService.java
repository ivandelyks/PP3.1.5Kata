package ru.kazachkov.springboot.security.services;


import ru.kazachkov.springboot.security.models.Role;

import java.util.List;

public interface RoleService {
    void saveRole(Role roleAdmin);
}
