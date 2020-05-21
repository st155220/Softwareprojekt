package de.hohenheim.realdemocracy.controller;

import de.hohenheim.realdemocracy.entity.Citizen;
import de.hohenheim.realdemocracy.entity.User;
import de.hohenheim.realdemocracy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PasswortVergessenController {

    @Autowired
    private UserService userService;

    @GetMapping("/passwortVergessen")
    public String showPaswortVergessen() {
        return "passwortVergessen";
    }

    @PostMapping("/passwortVergessen/login")
    public String passwortAendern(HttpServletRequest req, Model model) {

        String e_mail = req.getParameter("e_mail");
        String ausweisnummer = req.getParameter("ausweisnummer");
        String neues_passwort = req.getParameter("neues_passwort");
        String passwort_bestaetigen = req.getParameter("passwort_bestaetigen");

        if (!(neues_passwort.equals(passwort_bestaetigen))){
            return "passwortVergessen";
        }

        for (User user : userService.find_All_Users()){
            if (user instanceof Citizen){
                Citizen citizen = (Citizen) user;
                if (citizen.get_E_Mail().equals(e_mail) && citizen.get_Ausweisnummer().equals(ausweisnummer)){
                    userService.change_passwort(user.get_User_Id(), neues_passwort);
                    return "login";
                }
            }
        }
        return "passwortVergessen";
    }
}
