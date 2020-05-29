package de.hohenheim.realdemocracy.controller;

import de.hohenheim.realdemocracy.entity.Politician;
import de.hohenheim.realdemocracy.entity.User;
import de.hohenheim.realdemocracy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PasswortVergessenController {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/passwortVergessen")
    public String showPaswortVergessen() {
        return "passwortVergessen";
    }

    @PostMapping("/passwortVergessen/login")
    public String passwortAendern(HttpServletRequest req, Model model) {
        String username = req.getParameter("e_mail");
        String ausweisnummer = req.getParameter("ausweisnummer");
        String neues_passwort = req.getParameter("neues_passwort");
        String passwort_bestaetigen = req.getParameter("passwort_bestaetigen");

        if (!User.passwort_Format_Passt(neues_passwort)) {
            return "passwortVergessen";
        }

        if (!(neues_passwort.equals(passwort_bestaetigen))) {
            return "passwortVergessen";
        }

        for (User user : userService.find_All_Users()) {
            if (!(user instanceof Politician) && user.getUsername().equals(username) && user.get_Ausweisnummer().equals(ausweisnummer)) {
                userService.change_passwort(user.get_User_Id(), passwordEncoder.encode(neues_passwort));
                return "login";
            }
        }
        return "passwortVergessen";
    }
}
