package de.hohenheim.realdemocracy.repository;

import de.hohenheim.realdemocracy.entity.Abstimmung;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbstimmungRepository extends JpaRepository<Abstimmung, Integer> {
}
