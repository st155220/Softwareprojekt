package de.hohenheim.realdemocracy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrierungController {

    @GetMapping("/registrierung")
    public String showRegister() {
        return "registrierung";
    }

    @PostMapping("/registrierung/login")
    public String register(Model model) {
        return "login";
    }



}
