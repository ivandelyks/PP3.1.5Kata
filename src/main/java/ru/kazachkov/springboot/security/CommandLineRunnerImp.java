package ru.kazachkov.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kazachkov.springboot.security.models.Role;
import ru.kazachkov.springboot.security.models.User;
import ru.kazachkov.springboot.security.repositories.RoleRepository;
import ru.kazachkov.springboot.security.repositories.UserRepository;


import java.util.HashSet;
import java.util.Set;

@Component
public class CommandLineRunnerImp implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @Autowired
    public CommandLineRunnerImp(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        Set<Role> adminRoles = new HashSet<>();
        Set<Role> userRoles = new HashSet<>();
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);
        adminRoles.add(roleAdmin);
        adminRoles.add(roleUser);
        userRoles.add(roleUser);

        User adminUser = new User("Ivan", "Kazachkov", "Ivan", passwordEncoder.encode("100"), "ivan@mail.ru", Set.of(roleAdmin, roleUser));
//        User adminUser = new User("Ivan", "Kazachkov", "Ivan", "100", "ivan@mail.ru", Set.of(roleAdmin, roleUser));
//        User adminUser = new User("Ivan", "Kazachkov", "Ivan", "100", "ivan@mail.ru", Set.of(roleAdmin, roleUser));
//        adminUser.setRoles(adminRoles);
//        user1.setRoles(userRoles);
//        user2.setRoles(userRoles);
        userRepository.save(adminUser);
//        userRepository.save(user1);
//        userRepository.save(user2);
    }
}
