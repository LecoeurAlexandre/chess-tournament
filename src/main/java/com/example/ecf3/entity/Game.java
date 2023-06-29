package com.example.ecf3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // partie terminée
    private Boolean played;
    //joueur 1 gagnant
    private Boolean p1Win;
    //joueur 2 gagnant
    private Boolean p2Win;
    //match nul
    private Boolean draw;
    @ManyToMany
    private List<Player> players;
}
