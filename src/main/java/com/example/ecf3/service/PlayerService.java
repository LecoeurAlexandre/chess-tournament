package com.example.ecf3.service;

import com.example.ecf3.entity.Player;
import com.example.ecf3.exception.EmptyFieldsException;
import com.example.ecf3.exception.NotloggedException;
import com.example.ecf3.exception.UserExistException;
import com.example.ecf3.exception.UserNotExistException;
import com.example.ecf3.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private LoginService loginService;

    public boolean signUp(String lastName, String firstName, String pseudo, String password) throws UserExistException {
        try {
            playerRepository.findByPseudo(pseudo);
            throw new UserExistException();
        }
        catch (Exception ex) {
            Player player = Player.builder().lastName(lastName).firstName(firstName).pseudo(pseudo).password(password).isAdmin(false).build();
            playerRepository.save(player);
            return player.getId() > 0;
        }
    }

    public boolean signIn(String pseudo, String password) throws UserNotExistException {
        try {
            Player player = playerRepository.findByPseudoAndPassword(pseudo, password);
            return loginService.login(player);
        }catch (Exception ex) {
            throw new UserNotExistException();
        }
    }
    public List<Player> getPlayers() {
        return (List<Player>)playerRepository.findAll();
    }

    public List<Player> getPlayersById(String idPlayer1, String idPlayer2) {
        List<Integer> idList = Arrays.asList(Integer.parseInt(idPlayer1), Integer.parseInt(idPlayer2));
        return (List<Player>)playerRepository.findAllById(idList);}

    public Player getPlayerById(Integer id) throws UserNotExistException {
        try {
            return playerRepository.findById(id).get();
        } catch (Exception ex){
            throw new UserNotExistException();
        }
    }
    public boolean updatePlayer(Integer id,String lastName, String firstName, String pseudo, String password) throws UserExistException, EmptyFieldsException, NotloggedException {
        if (loginService.isLogged()) {
            if(pseudo!= null || password != null) {
                try {
                    playerRepository.findByPseudo(pseudo);
                    throw new UserExistException();
                }
                catch (Exception ex) {
                    Player player = playerRepository.findById(id).get();
                    player.setLastName(lastName);
                    player.setFirstName(firstName);
                    player.setPseudo(pseudo);
                    player.setPassword(password);
                    playerRepository.save(player);
                    return true;
                }
            } throw EmptyFieldsException.with("pseudo","password");
        } throw new NotloggedException();
    }
}
