package de.hohenheim.realdemocracy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrierungController {

    @GetMapping("/register")
    public String showRegister() {
        return "registrierung";
    }



}
