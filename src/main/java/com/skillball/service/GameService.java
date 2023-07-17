package com.skillball.service;

import com.skillball.entity.Game;
import com.skillball.entity.User;
import com.skillball.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    private Game currentGame;

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    public void deleteGame(Game game) {
        gameRepository.delete(game);
    }

    public List<Game> findAllGame() {
        return gameRepository.findAll();
    }

    public List<Game> listRelevantGames(User user) {
        List<Game> games = new ArrayList<>();
        for (Game game : findAllGame()) {
            if (game.getUser() == user) {
                games.add(game);
            }
        }
        games.sort(new GameComparator());
        return games;
    }

    public Game getGameByGameId(Integer gameId) {
        return gameRepository.findByGameId(gameId);
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    private class GameComparator implements Comparator<Game> {
        @Override
        public int compare(Game game1, Game game2) {
            return game2.getTimeStamp().compareTo(game1.getTimeStamp());
        }
    }
}