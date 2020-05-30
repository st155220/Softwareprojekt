package de.hohenheim.realdemocracy.controller;

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
public class UsernameVergessenController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/usernameVergessen")
    public String showUsernameVergessen() { return "usernameVergessen";}

    @PostMapping("/usernameVergessen/login")
    public String usernameAendern(HttpServletRequest req, Model model) {
        String ausweisnummer = req.getParameter("ausweisnummer");
        String password = req.getParameter("password");
        String newUsername = req.getParameter("newUsername");
        String usernameBestaetigen = req.getParameter("usernameBestaetigen");

        User user = userService.getUserByAusweisnummer(ausweisnummer);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            return "usernameVergessen";
        }
        if (newUsername.equals("") || !(newUsername.equals(usernameBestaetigen))) {
            return "usernameVergessen";
        }
        userService.changeUsername(user.getUserId(), newUsername);
        return "login";
    }
}
