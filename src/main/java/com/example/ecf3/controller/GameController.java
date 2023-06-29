package com.example.ecf3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GameController {
    @GetMapping("")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        return mv;
    }
}