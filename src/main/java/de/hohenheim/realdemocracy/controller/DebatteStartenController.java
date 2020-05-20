package de.hohenheim.realdemocracy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DebatteStartenController {

    @GetMapping("/debatteStarten")
    public String showDebatteStarten() {
        return "debatteStarten";
    }

    @PostMapping("/debatteStarten/home")
    public String register(Model model) {
        return "home";
    }

}
