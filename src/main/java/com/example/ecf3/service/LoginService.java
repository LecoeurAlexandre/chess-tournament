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
        httpSession.setAttribute("isLogged", true);
        httpSession.setAttribute("fullName", player.getFirstName() + " "+player.getLastName());
        httpSession.setAttribute("isAdmin", player.getIsAdmin());
        httpSession.setAttribute("userId", player.getId());
        return true;
    }
}
