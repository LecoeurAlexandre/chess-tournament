package com.example.ecf3.service;

import com.example.ecf3.entity.Game;
import com.example.ecf3.entity.Player;
import com.example.ecf3.exception.SamePlayerException;
import com.example.ecf3.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerService playerService;

    public boolean createGame(String idPlayer1, String idPlayer2) throws SamePlayerException {
        if (idPlayer1.equals(idPlayer2)){
            throw new SamePlayerException();
        } else {
            List<Player> gamePlayers = (List<Player>) playerService.getPlayersById(idPlayer1, idPlayer2);
            Game game = Game.builder().played(false).p1Win(false).p2Win(false).draw(false).players(gamePlayers).build();
            System.out.println(game);
            gameRepository.save(game);
        }
        return true;
    }
    public List<Game> getGames() {
        return (List<Game>)gameRepository.findAll();
    }

    public List<Game> getPlayedGames() {
        return (List<Game>)gameRepository.findPlayedGames();
    }
    public List<Game> getNotPlayedGames() {
        return (List<Game>)gameRepository.findNotPlayedGames();
    }
}
