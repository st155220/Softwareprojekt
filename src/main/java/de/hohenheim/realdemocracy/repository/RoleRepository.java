package de.hohenheim.realdemocracy.repository;

import de.hohenheim.realdemocracy.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
