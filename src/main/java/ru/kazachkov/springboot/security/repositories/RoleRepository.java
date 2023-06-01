package ru.kazachkov.springboot.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kazachkov.springboot.security.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
