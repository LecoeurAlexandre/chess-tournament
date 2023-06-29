package com.example.ecf3.controller;

import com.example.ecf3.exception.UserExistException;
import com.example.ecf3.exception.UserNotExistException;
import com.example.ecf3.service.PlayerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("user")
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private HttpServletResponse response;

    @GetMapping("connexion")
    public ModelAndView connexion() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }
    @GetMapping("/signin")
    public ModelAndView signIn() {
        ModelAndView mv = new ModelAndView("sign-in-form");
        return mv;
    }
    @PostMapping("/signin")
    public String signIn(@RequestParam String pseudo, @RequestParam String password) throws UserNotExistException, IOException {
        if(playerService.signIn(pseudo, password)) {
            return "redirect:/";
        }
        return null;
    }

    @GetMapping("signup")
    public ModelAndView signUp() {
        ModelAndView mv = new ModelAndView("sign-up-form");
        return mv;
    }
    @PostMapping("signup")
    public String postSignUp(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String pseudo, @RequestParam String password) throws UserExistException, IOException, UserExistException {
        if(playerService.signUp(firstName, lastName, pseudo, password)) {
            return "redirect:/user/signin";
        }
        return null;
    }
}
