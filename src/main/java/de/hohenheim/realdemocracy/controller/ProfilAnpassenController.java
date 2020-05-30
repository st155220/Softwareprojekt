package de.hohenheim.realdemocracy.controller;

import de.hohenheim.realdemocracy.entity.*;
import de.hohenheim.realdemocracy.service.HelpService;
import de.hohenheim.realdemocracy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfilAnpassenController {

    @Autowired
    private UserService userService;
    @Autowired
    private HelpService helpService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/profilAnpassen")
    public String showProfilAnpassen() {
        return "profilAnpassen";
    }

    /**
     *
     *
     *
     */
    @PostMapping("/usernameAnpassen/home")
    public String usernameAnpassen(HttpServletRequest req, Model model) {
        String newUsername = req.getParameter("newUsername");
        String usernameBestaetigen = req.getParameter("usernameBestaetigen");

        if (newUsername.equals("") || !(newUsername.equals(usernameBestaetigen))
                || userService.existsByUsername(newUsername)) {
            return "profilAnpassen";
        }

        User user = userService.getCurrentUser();
        userService.changeUsername(user.getUserId(), newUsername);

        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        return "login";
    }

    /**
     *
     *
     *
     */
    @PostMapping("/passwordAnpassen/home")
    public String passwortAnpassen(HttpServletRequest req, Model model) {
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");
        String passwordBestaetigen = req.getParameter("passwordBestaetigen");

        User user = userService.getCurrentUser();
        if (!(helpService.passwortFormatPasst(newPassword) && newPassword.equals(passwordBestaetigen)
                && passwordEncoder.matches(oldPassword, user.getPassword()))) {
            return "profilAnpassen";
        }

        userService.changePassword(user.getUserId(), passwordEncoder.encode(newPassword));

        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user.getUsername(), newPassword);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authReq);

        model.addAttribute("debatten", helpService.getDebattes());
        model.addAttribute("username", userService.getCurrentUser().getUsername());
        return "home";
    }

    /**
     *
     *
     *
     */
    @PostMapping("/bundeslandAnpassen/home")
    public String bundeslandAnpassen(HttpServletRequest req, Model model) {
        Bundesland bundesland = helpService.getBundesland(req.getParameter("bundesland"));
        User user = userService.getCurrentUser();
        userService.changeBundesland(user.getUserId(), bundesland);

        model.addAttribute("debatten", helpService.getDebattes());
        model.addAttribute("username", userService.getCurrentUser().getUsername());
        return "home";
    }
}
