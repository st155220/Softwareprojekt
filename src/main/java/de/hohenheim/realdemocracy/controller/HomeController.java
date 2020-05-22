package de.hohenheim.realdemocracy.controller;

import de.hohenheim.realdemocracy.entity.Debatte;
import de.hohenheim.realdemocracy.entity.Politician;
import de.hohenheim.realdemocracy.entity.User;
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
    @Autowired
    DebatteService debatteService;

    @GetMapping("/home")
    @RequestMapping(value="/home", method = RequestMethod.GET)
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

    @PostMapping("/debatteOeffnen")
    public String openDebatte(HttpServletRequest req, Model model) {
        Debatte debatte = debatteService.get_Debatte_By_Id(Integer.parseInt(req.getParameter("debatten_Id")));
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

    @PostMapping("/debatteSchliessen")
    public String closeDebatte(Model model) {
        List<Debatte> debatten = debatteService.find_All_Debates();
        model.addAttribute("debatten", debatten);
        return "home";
    }

}
