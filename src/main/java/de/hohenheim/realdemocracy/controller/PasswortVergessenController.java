package de.hohenheim.realdemocracy.controller;

import de.hohenheim.realdemocracy.entity.User;
import de.hohenheim.realdemocracy.service.HelpService;
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
    private HelpService helpService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/passwortVergessen")
    public String showPaswortVergessen() {
        return "passwortVergessen";
    }

    @PostMapping("/passwortVergessen/login")
    public String passwortAendern(HttpServletRequest req, Model model) {
        String username = req.getParameter("username");
        String ausweisnummer = req.getParameter("ausweisnummer");
        String newPassword = req.getParameter("newPassword");
        String passwordBestaetigen = req.getParameter("passwordBestaetigen");

        User user = userService.getUserByUsername(username);
        if (user == null || !user.getAusweisnummer().equals(ausweisnummer)) {
            return "passwortVergessen";
        }
        if (!helpService.passwortFormatPasst(newPassword) || !(newPassword.equals(passwordBestaetigen))) {
            return "passwortVergessen";
        }

        userService.changePassword(user.getUserId(), passwordEncoder.encode(newPassword));
        return "login";
    }
}
