package de.hohenheim.realdemocracy.repository;

import de.hohenheim.realdemocracy.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, String> {

    Person findByAusweisnummer(String ausweisnummer);
}
