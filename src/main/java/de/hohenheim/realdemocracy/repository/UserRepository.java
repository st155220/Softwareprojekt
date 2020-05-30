package de.hohenheim.realdemocracy.repository;

import de.hohenheim.realdemocracy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    User findByAusweisnummer(String ausweisnummer);
    boolean existsByAusweisnummer(String ausweisnummer);
    boolean existsByUsername(String username);
}