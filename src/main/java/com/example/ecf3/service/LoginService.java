package com.example.ecf3.service;

import com.example.ecf3.entity.Player;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    HttpSession httpSession;

    public boolean login(Player player) {
        httpSession.setMaxInactiveInterval(300);
        httpSession.setAttribute("isLogged", true);
        httpSession.setAttribute("player", player);
        httpSession.setAttribute("isAdmin", player.getIsAdmin());
        httpSession.setAttribute("userId", player.getId());
        return true;
    }
    public boolean isLogged() {
        return httpSession.getAttribute("isLogged") != null && (boolean)httpSession.getAttribute("isLogged") == true;
    }
    public boolean isAdmin() {
        return httpSession.getAttribute("isLogged") != null && (boolean)httpSession.getAttribute("isAdmin") == true;
    }

    public Player getInfosPlayer() {
        return (Player) httpSession.getAttribute("player");
    }
}
