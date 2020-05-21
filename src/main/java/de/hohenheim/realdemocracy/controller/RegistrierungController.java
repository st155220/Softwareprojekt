package de.hohenheim.realdemocracy.controller;

import de.hohenheim.realdemocracy.entity.Bundesland;
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
public class RegistrierungController {

    @Autowired
    private UserService userService;

    @GetMapping("/registrierung")
    public String showRegister() {
        return "registrierung";
    }

    @PostMapping("/registrierung/login")
    public String register(HttpServletRequest req, Model model) {
        String nachname = req.getParameter("nachname");
        String ausweisnummer = req.getParameter("ausweisnummer");
        String e_mail = req.getParameter("e_mail");
        String e_mail_bestaetigen = req.getParameter("e_mail_bestaetigen");
        String passwort = req.getParameter("passwort");
        String passwort_bestaetigen = req.getParameter("passwort_bestaetigen");

        Bundesland bundesland = Bundesland.ALLE;

        switch (req.getParameter("bundesland")) {
            case "alle":
                bundesland = Bundesland.ALLE;
                break;
            case "baden_wuerttemberg":
                bundesland = Bundesland.Baden_Wuerttemberg;
                break;
            case "bayern":
                bundesland = Bundesland.Bayern;
                break;
            case "berlin":
                bundesland = Bundesland.Berlin;
                break;
            case "brandenburg":
                bundesland = Bundesland.Brandenburg;
                break;
            case "bremen":
                bundesland = Bundesland.Bremen;
                break;
            case "hamburg":
                bundesland = Bundesland.Hamburg;
                break;
            case "hessen":
                bundesland = Bundesland.Hessen;
                break;
            case "mecklenburg_vorpommen":
                bundesland = Bundesland.Mecklenburg_Vorpommen;
                break;
            case "niedersachsen":
                bundesland = Bundesland.Niedersachsen;
                break;
            case "nordrhein_westfalen":
                bundesland = Bundesland.Nordrhein_Westfalen;
                break;
            case "rheinland_pfalz":
                bundesland = Bundesland.Rheinland_Pfalz;
                break;
            case "saarland":
                bundesland = Bundesland.Saarland;
                break;
            case "sachsen_anhalt":
                bundesland = Bundesland.Sachsen_Anhalt;
                break;
            case "sachsen":
                bundesland = Bundesland.Sachsen;
                break;
            case "schleswig-holstein":
                bundesland = Bundesland.Schleswig_Holstein;
                break;
            case "thueringen":
                bundesland = Bundesland.Thueringen;
                break;
        }


        if (!(e_mail.equals(e_mail_bestaetigen) && passwort.equals(passwort_bestaetigen))) {
            return "registrierung";
        }

        Citizen newUser = new Citizen();
        newUser.set_Ausweisnummer(ausweisnummer);
        newUser.set_E_Mail(e_mail);
        newUser.set_Passwort(passwort);
        userService.save_User(newUser);


        return "login";
    }


}
