package de.hohenheim.realdemocracy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PasswortVergessenController {

    @GetMapping("/passwortVergessen")
    public String showPaswortVergessen() {
        return "passwortVergessen";
    }

    @PostMapping("/passwortVergessen/login")
    public String register(Model model) {
        return "login";
    }

}
