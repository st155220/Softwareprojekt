package de.hohenheim.realdemocracy.controller;

import de.hohenheim.realdemocracy.entity.*;
import de.hohenheim.realdemocracy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    public static Debatte currentDebatte;
    public static Sektor currentSektor;

    @Autowired
    private UserService userService;
    @Autowired
    private DebatteService debatteService;
    @Autowired
    private HelpService helpService;

    @GetMapping("/home")
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showHome(Model model) {
        model.addAttribute("debatten", helpService.getDebattes());
        model.addAttribute("username", helpService.getCurrentUsername());
        return "home";
    }

    @PostMapping("/home/change")
    public String changeSektor(HttpServletRequest req, Model model) {
        Sektor sektor = helpService.getSektor(req.getParameter("sektor"));
        currentSektor = sektor;
        model.addAttribute("debatten", helpService.getDebattes());
        model.addAttribute("username", helpService.getCurrentUsername());
        return "home";
    }

    @PostMapping("/home/debatte")
    public String openDebatte(HttpServletRequest req, Model model) {
        Debatte debatte = debatteService.getDebatteById(Integer.parseInt(req.getParameter("debatten_Id")));
        Politician ersteller = debatte.getErsteller();
        currentDebatte = debatte;
        model.addAttribute("titel", debatte.getTitel());
        model.addAttribute("sektor", ersteller.getSektor());
        model.addAttribute("nachname", ersteller.getNachname());
        model.addAttribute("vorname", ersteller.getVorname());
        model.addAttribute("titel", debatte.getTitel());
        model.addAttribute("problemstellung", debatte.getProblemstellung());
        model.addAttribute("loesungsstrategie", debatte.getLoesungsstrategie());
        model.addAttribute("stichtag", debatte.getStichtag());
        model.addAttribute("anzahl_Zustimmungen", debatte.getAnzahlZustimmungen());
        model.addAttribute("anzahl_Ablehnungen", debatte.getAnzahlAblehnungen());
        return "debatte";
    }
}
