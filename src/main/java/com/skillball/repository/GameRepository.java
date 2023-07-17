package com.skillball.repository;

import com.skillball.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
    Game findByGameId(Integer gameId);
}