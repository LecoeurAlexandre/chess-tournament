package com.example.ecf3.repository;

import com.example.ecf3.entity.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Integer> {
    public Player findByPseudoAndPassword(String pseudo, String password);
    public Player findByPseudo(String pseudo);
}
