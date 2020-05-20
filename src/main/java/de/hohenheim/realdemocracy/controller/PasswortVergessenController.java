package de.hohenheim.realdemocracy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PasswortVergessenController {

    @GetMapping("/passwortVergessen")
    public String showPaswortVergessen() {
        return "passwortVergessen";
    }

}
