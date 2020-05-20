package de.hohenheim.realdemocracy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String showHome() {
        return "home";
    }

    @PostMapping("/home/login")
    public String register(Model model) {
        return "login";
    }

}
