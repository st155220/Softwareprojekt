package de.hohenheim.realdemocracy.controller;

import de.hohenheim.realdemocracy.entity.*;
import de.hohenheim.realdemocracy.service.DebatteService;
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
import java.util.List;

@Controller
public class ProfilAnpassenController {

    @Autowired
    private UserService userService;
    @Autowired
    private DebatteService debatteService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/profilAnpassen")
    public String showProfilAnpassen() {
        return "profilAnpassen";
    }

    @PostMapping("/emailAnpassen/home")
    public String emailAnpassen(HttpServletRequest req, Model model) {
        String neue_e_mail = req.getParameter("neue_e_mail");
        String e_mail_bestaetigen = req.getParameter("e_mail_bestaetigen");

        if (!User.email_Format_Passt(neue_e_mail) || !(neue_e_mail.equals(e_mail_bestaetigen))) {
            return "profilAnpassen";
        }

        for (User user : userService.find_All_Users()){
            if (user.getUsername().equals(neue_e_mail)){
                return "profilAnpassen";
            }
        }

        User user = userService.getCurrentUser();
        userService.change_email(user.get_User_Id(), neue_e_mail);

        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(neue_e_mail, user.getPassword());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authReq);

        List<Debatte> debatten = debatteService.find_All_Debates();
        model.addAttribute("debatten", debatten);
        model.addAttribute("username", user.getUsername());
        return "home";
    }

    @PostMapping("/passwortAnpassen/home")
    public String passwortAnpassen(HttpServletRequest req, Model model) {
        String altes_passwort = req.getParameter("altes_passwort");
        String neues_passwort = req.getParameter("neues_passwort");
        String passwort_bestaetigen = req.getParameter("passwort_bestaetigen");


        User user = userService.getCurrentUser();
        if (!(User.passwort_Format_Passt(neues_passwort) && neues_passwort.equals(passwort_bestaetigen)
                && passwordEncoder.matches(altes_passwort, user.getPassword()))) {
            return "profilAnpassen";
        }

        userService.change_passwort(user.get_User_Id(), passwordEncoder.encode(neues_passwort));

        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user.getUsername(), neues_passwort);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authReq);

        List<Debatte> debatten = debatteService.find_All_Debates();
        model.addAttribute("debatten", debatten);
        model.addAttribute("email", user.getUsername());
        return "home";
    }

    @PostMapping("/bundeslandAnpassen/home")
    public String bundeslandAnpassen(HttpServletRequest req, Model model) {
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

        User user = userService.getCurrentUser();
        userService.change_Bundesland(user.get_User_Id(), bundesland);
        List<Debatte> debatten = debatteService.find_All_Debates();
        model.addAttribute("debatten", debatten);
        model.addAttribute("email", user.getUsername());
        return "home";
    }
}
