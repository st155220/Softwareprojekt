package de.hohenheim.realdemocracy.controller;

import de.hohenheim.realdemocracy.entity.Abstimmung;
import de.hohenheim.realdemocracy.entity.Debatte;
import de.hohenheim.realdemocracy.entity.Politician;
import de.hohenheim.realdemocracy.service.AbstimmungService;
import de.hohenheim.realdemocracy.service.DebatteService;
import de.hohenheim.realdemocracy.service.HelpService;
import de.hohenheim.realdemocracy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DebatteController {
    @Autowired
    private UserService userService;
    @Autowired
    private DebatteService debatteService;
    @Autowired
    private AbstimmungService abstimmungService;
    @Autowired
    private HelpService helpService;

    @PostMapping("/debatte/home")
    public String closeDebatte(HttpServletRequest req, Model model) {
        String action = req.getParameter("submit");
        Debatte debatte = HomeController.currentDebatte;

        if (action.equals("zustimmen")) {
            if (helpService.schonAbgestimmt(debatte) || helpService.nichtWahlberechtigt(debatte.getBundesland())) {
                Politician ersteller = debatte.getErsteller();
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
            debatteService.addZustimmung(debatte.getDebatteId());
            Abstimmung abstimmung = new Abstimmung();
            abstimmung.setDebatteId(debatte.getDebatteId());
            abstimmung.setUserId(userService.getCurrentUser().getUserId());
            abstimmungService.saveAbstimmung(abstimmung);
        }

        if (action.equals("ablehnen")) {
            if (helpService.schonAbgestimmt(debatte) || helpService.nichtWahlberechtigt(debatte.getBundesland())) {
                Politician ersteller = debatte.getErsteller();
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
            debatteService.addAblehnung(debatte.getDebatteId());
            Abstimmung abstimmung = new Abstimmung();
            abstimmung.setDebatteId(debatte.getDebatteId());
            abstimmung.setUserId(userService.getCurrentUser().getUserId());
            abstimmungService.saveAbstimmung(abstimmung);
        }

        if (action.equals("loeschen")) {
            if (debatte.getErsteller().getUserId() != userService.getCurrentUser().getUserId()) {
                Politician ersteller = debatte.getErsteller();
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
            debatteService.deleteDebatteById(debatte.getDebatteId());
        }
        HomeController.currentDebatte = null;
        model.addAttribute("debatten", helpService.getDebattes());
        model.addAttribute("username", userService.getCurrentUser().getUsername());
        return "home";
    }
}
