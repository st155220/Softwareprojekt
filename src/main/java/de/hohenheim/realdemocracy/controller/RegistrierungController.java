package de.hohenheim.realdemocracy.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class RegistrierungController {

    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }



}
