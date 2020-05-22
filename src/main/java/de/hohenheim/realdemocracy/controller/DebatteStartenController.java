package de.hohenheim.realdemocracy.controller;

import de.hohenheim.realdemocracy.entity.*;
import de.hohenheim.realdemocracy.service.DebatteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class DebatteStartenController {

    @Autowired
    private DebatteService debatteService;

    @GetMapping("/debatteStarten")
    public String showDebatteStarten() {
        return "debatteStarten";
    }

    @PostMapping("/debatteStarten/home")
    public String debatteStarten(HttpServletRequest req, Model model) {

        Kategorie kategorie = Kategorie.ALLE;
        Thema thema = Thema.ALLE;
        String titel = req.getParameter("titel");
        String problemstellung = req.getParameter("problemstellung");
        String loesungsstrategie = req.getParameter("loesungsstrategie");
        String stichtag = req.getParameter("stichtag");
        Bundesland bundesland = Bundesland.ALLE;

        switch (req.getParameter("kategorie")) {
            case "alle":
                kategorie = Kategorie.ALLE;
                break;
        }

        switch (req.getParameter("thema")) {
            case "alle":
                thema = Thema.ALLE;
                break;
        }

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

        if (titel.equals("") || problemstellung.equals("") || loesungsstrategie.equals("") || stichtag.equals("")
                || !(LoginController.currentUser instanceof Politician)) {
            return "debatteStarten";
        }

        Debatte new_Debatte = new Debatte();
        new_Debatte.setKategorie(kategorie);
        new_Debatte.setThema(thema);
        new_Debatte.setErsteller((Politician) LoginController.currentUser);
        new_Debatte.setBundesland(bundesland);
        new_Debatte.setTitel(titel);
        new_Debatte.setProblemstellung(problemstellung);
        new_Debatte.setLoesungsstrategie(loesungsstrategie);
        new_Debatte.setStichtag(stichtag);
        debatteService.save_Debatte(new_Debatte);

        List<Debatte> debatten = debatteService.find_All_Debates();
        model.addAttribute("debatten", debatten);
        return "home";
    }

}
