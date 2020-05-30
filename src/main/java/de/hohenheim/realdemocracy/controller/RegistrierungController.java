package de.hohenheim.realdemocracy.controller;

import de.hohenheim.realdemocracy.entity.Bundesland;
import de.hohenheim.realdemocracy.entity.Person;
import de.hohenheim.realdemocracy.entity.User;
import de.hohenheim.realdemocracy.service.HelpService;
import de.hohenheim.realdemocracy.service.PersonService;
import de.hohenheim.realdemocracy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegistrierungController {
    @Autowired
    private UserService userService;
    @Autowired
    private PersonService personService;
    @Autowired
    private HelpService helpService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/registrierung")
    public String showRegister() {
        return "registrierung";
    }

    @PostMapping("/registrierung/login")
    public String register(HttpServletRequest req, Model model) {
        String nachname = req.getParameter("nachname");
        String ausweisnummer = req.getParameter("ausweisnummer");
        String username = req.getParameter("username");
        String usernameBestaetigen = req.getParameter("usernameBestaetigen");
        String password = req.getParameter("password");
        String passwordBestaetigen = req.getParameter("passwordBestaetigen");
        String datenschutzhinweise = req.getParameter("datenschutzhinweise");
        Bundesland bundesland = helpService.getBundesland(req.getParameter("bundesland"));

        if (username.equals("") || !helpService.passwortFormatPasst(password) || datenschutzhinweise == null) {
            return "registrierung";
        }

        if (!username.equals(usernameBestaetigen) || !password.equals(passwordBestaetigen)) {
            return "registrierung";
        }

        if (userService.existsByUsername(username) || userService.existsByAusweisnummer(ausweisnummer)) {
            return "registrierung";
        }

        Person person = personService.findByAusweisnummer(ausweisnummer);

        if (person == null || !person.getNachname().equals(nachname)) {
            return "registrierung";
        }

        User newUser = new User();
        newUser.setAusweisnummer(ausweisnummer);
        newUser.setBundesland(bundesland);
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setEnabled(true);
        userService.saveUser(newUser);
        return "login";
    }
}
