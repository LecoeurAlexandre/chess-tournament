package com.example.ecf3.repository;

import com.example.ecf3.entity.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {
    @Query(
            value = "SELECT * FROM game WHERE played = true",
            nativeQuery = true)
    List<Game> findPlayedGames();
    @Query(
            value = "SELECT * FROM game WHERE played = false",
            nativeQuery = true)
    List<Game> findNotPlayedGames();
}
