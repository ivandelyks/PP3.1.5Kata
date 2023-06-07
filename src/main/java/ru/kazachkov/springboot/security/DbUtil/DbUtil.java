package ru.kazachkov.springboot.security.DbUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kazachkov.springboot.security.models.Role;
import ru.kazachkov.springboot.security.models.User;
import ru.kazachkov.springboot.security.services.RoleService;
import ru.kazachkov.springboot.security.services.UsersService;


import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class DbUtil {

    private final RoleService roleService;
    private final UsersService usersService;

    @Autowired
    public DbUtil(RoleService roleService, UsersService usersService) {
        this.roleService = roleService;
        this.usersService = usersService;
    }

    @PostConstruct
    public void initialization() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);
        User admin = new User("admin", "admin", "Ivan", "Kazachkov", Set.of(roleAdmin, roleUser));
        usersService.saveUser(admin);

        roleService.saveRole(roleUser);
        User user = new User("user","user", "Fedor", "Kazachkov", Set.of(roleUser));

        usersService.saveUser(user);
    }
}
