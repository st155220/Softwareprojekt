package de.hohenheim.realdemocracy.controller;

import de.hohenheim.realdemocracy.entity.User;
import de.hohenheim.realdemocracy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    public static User currentUser;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String start() {
        return "login";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login/home")
    public String logIn(HttpServletRequest req, Model model) {
        String e_mail = req.getParameter("e_mail");
        String passwort = req.getParameter("passwort");

        for (User user : userService.find_All_Users()){
            if (user.get_E_Mail().equals(e_mail) && user.getPasswort().equals(passwort)){
                model.addAttribute("currentUser", user);
                currentUser = user;
                return "home";
            }
        }
        return "login";
    }
}