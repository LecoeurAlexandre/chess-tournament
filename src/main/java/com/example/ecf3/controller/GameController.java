package com.example.ecf3.controller;

import com.example.ecf3.entity.Game;
import com.example.ecf3.exception.SamePlayerException;
import com.example.ecf3.exception.UserNotExistException;
import com.example.ecf3.service.GameService;
import com.example.ecf3.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
public class GameController {
    @Autowired
    private GameService gameservice;
    @Autowired
    private PlayerService playerService;
    @GetMapping("")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        //List<Game> playedGames = gameservice.getGames().getPlayed(true);
        mv.addObject("playedGames", (List<Game>) gameservice.getPlayedGames());
        mv.addObject("notPlayedGames", (List<Game>) gameservice.getNotPlayedGames());
        return mv;
    }
    @GetMapping("/games")
    public ModelAndView games() {
        ModelAndView mv = new ModelAndView("match-management");
        return mv;
    }
    @GetMapping("/games/create")
    public ModelAndView createGame() {
        ModelAndView mv = new ModelAndView("create-match-form");
        mv.addObject("players", playerService.getPlayers());
        return mv;
    }
    @PostMapping("/games/create")
    public String createGame(@RequestParam String player1, @RequestParam String player2) throws SamePlayerException {
        if(gameservice.createGame(player1, player2)) {
            return "redirect:/user/profile";
        }
        return null;
    }

}
