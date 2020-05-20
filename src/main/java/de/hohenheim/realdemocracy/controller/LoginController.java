package de.hohenheim.realdemocracy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    /**
     * Zeigt die Startseite Ihrer Anwendung.
     * @return login-Seite.
     */
    @GetMapping("/")
    public String start() {
        return "login";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }


}