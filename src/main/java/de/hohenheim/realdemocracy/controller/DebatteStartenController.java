package de.hohenheim.realdemocracy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DebatteStartenController {

    @GetMapping("/debatteStarten")
    public String showDebatteStarten() {
        return "debatteStarten";
    }
}
