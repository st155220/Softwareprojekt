package de.hohenheim.realdemocracy.controller;

import de.hohenheim.realdemocracy.entity.Abstimmung;
import de.hohenheim.realdemocracy.entity.Debatte;
import de.hohenheim.realdemocracy.entity.Politician;
import de.hohenheim.realdemocracy.service.AbstimmungService;
import de.hohenheim.realdemocracy.service.DebatteService;
import de.hohenheim.realdemocracy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {

    public static Debatte currentDebatte;

    @Autowired
    private UserService userService;
    @Autowired
    private DebatteService debatteService;
    @Autowired
    private AbstimmungService abstimmungService;

    @GetMapping("/home")
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showHome(Model model) {
        List<Debatte> debatten = debatteService.find_All_Debates();
        model.addAttribute("debatten", debatten);
        return "home";
    }

    @PostMapping("/home/login")
    public String logOut(Model model) {
        LoginController.currentUser = null;
        return "login";
    }

    @PostMapping("/home/debatte")
    public String openDebatte(HttpServletRequest req, Model model) {
        Debatte debatte = debatteService.get_Debatte_By_Id(Integer.parseInt(req.getParameter("debatten_Id")));
        Politician ersteller = debatte.get_Ersteller();
        currentDebatte = debatte;
        model.addAttribute("titel", debatte.get_Titel());
        model.addAttribute("sektor", ersteller.get_Sektor());
        model.addAttribute("nachname", ersteller.get_Nachname());
        model.addAttribute("vorname", ersteller.get_Vorname());
        model.addAttribute("titel", debatte.get_Titel());
        model.addAttribute("problemstellung", debatte.get_Problemstellung());
        model.addAttribute("loesungsstrategie", debatte.get_Loesungsstrategie());
        model.addAttribute("stichtag", debatte.get_Stichtag());
        model.addAttribute("anzahl_Zustimmungen", debatte.get_Anzahl_Zustimmungen());
        model.addAttribute("anzahl_Ablehnungen", debatte.get_Anzahl_Ablehnungen());
        return "debatte";
    }

    @PostMapping("/debatte/home")
    public String closeDebatte(HttpServletRequest req, Model model) {
        String action = req.getParameter("submit");
        Debatte debatte = currentDebatte;

        if (action.equals("zustimmen")) {
            if (schon_Abgestimmt(debatte)){
                Politician ersteller = debatte.get_Ersteller();
                model.addAttribute("titel", debatte.get_Titel());
                model.addAttribute("sektor", ersteller.get_Sektor());
                model.addAttribute("nachname", ersteller.get_Nachname());
                model.addAttribute("vorname", ersteller.get_Vorname());
                model.addAttribute("titel", debatte.get_Titel());
                model.addAttribute("problemstellung", debatte.get_Problemstellung());
                model.addAttribute("loesungsstrategie", debatte.get_Loesungsstrategie());
                model.addAttribute("stichtag", debatte.get_Stichtag());
                model.addAttribute("anzahl_Zustimmungen", debatte.get_Anzahl_Zustimmungen());
                model.addAttribute("anzahl_Ablehnungen", debatte.get_Anzahl_Ablehnungen());
                return "debatte";
            }
            debatteService.add_Zustimmung(debatte.get_Debatte_Id());
            Abstimmung abstimmung = new Abstimmung();
            abstimmung.set_Debatte_Id(debatte.get_Debatte_Id());
            abstimmung.set_User_Id(LoginController.currentUser.get_User_Id());
            abstimmungService.save_Abstimmung(abstimmung);
        }

        if (action.equals("ablehnen")) {
            if (schon_Abgestimmt(debatte)){
                Politician ersteller = debatte.get_Ersteller();
                model.addAttribute("titel", debatte.get_Titel());
                model.addAttribute("sektor", ersteller.get_Sektor());
                model.addAttribute("nachname", ersteller.get_Nachname());
                model.addAttribute("vorname", ersteller.get_Vorname());
                model.addAttribute("titel", debatte.get_Titel());
                model.addAttribute("problemstellung", debatte.get_Problemstellung());
                model.addAttribute("loesungsstrategie", debatte.get_Loesungsstrategie());
                model.addAttribute("stichtag", debatte.get_Stichtag());
                model.addAttribute("anzahl_Zustimmungen", debatte.get_Anzahl_Zustimmungen());
                model.addAttribute("anzahl_Ablehnungen", debatte.get_Anzahl_Ablehnungen());
                return "debatte";
            }
            debatteService.add_Ablehnung(debatte.get_Debatte_Id());
            Abstimmung abstimmung = new Abstimmung();
            abstimmung.set_Debatte_Id(debatte.get_Debatte_Id());
            abstimmung.set_User_Id(LoginController.currentUser.get_User_Id());
           abstimmungService.save_Abstimmung(abstimmung);
        }

        if (action.equals("loeschen")) {
            if(debatte.get_Ersteller().get_User_Id() != LoginController.currentUser.get_User_Id()){
                Politician ersteller = debatte.get_Ersteller();
                model.addAttribute("titel", debatte.get_Titel());
                model.addAttribute("sektor", ersteller.get_Sektor());
                model.addAttribute("nachname", ersteller.get_Nachname());
                model.addAttribute("vorname", ersteller.get_Vorname());
                model.addAttribute("titel", debatte.get_Titel());
                model.addAttribute("problemstellung", debatte.get_Problemstellung());
                model.addAttribute("loesungsstrategie", debatte.get_Loesungsstrategie());
                model.addAttribute("stichtag", debatte.get_Stichtag());
                model.addAttribute("anzahl_Zustimmungen", debatte.get_Anzahl_Zustimmungen());
                model.addAttribute("anzahl_Ablehnungen", debatte.get_Anzahl_Ablehnungen());
                return "debatte";
            }
            debatteService.delete_Debatte_By_Id(debatte.get_Debatte_Id());
        }
        List<Debatte> debatten = debatteService.find_All_Debates();
        model.addAttribute("debatten", debatten);
        currentDebatte = null;
        return "home";
    }


    private boolean schon_Abgestimmt(Debatte debatte) {
        for(Abstimmung abstimmung : abstimmungService.find_All_Abstimmungen()) {
            if(abstimmung.get_Debatte_Id() == debatte.get_Debatte_Id()
                    && abstimmung.get_User_Id() == LoginController.currentUser.get_User_Id()){
                return true;
            }
        }

        return false;
    }


}
