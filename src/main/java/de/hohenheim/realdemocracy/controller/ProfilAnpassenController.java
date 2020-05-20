package de.hohenheim.realdemocracy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfilAnpassenController {

    @GetMapping("/profilAnpassen")
    public String showProfilAnpassen() { return "profilAnpassen"; }
}
