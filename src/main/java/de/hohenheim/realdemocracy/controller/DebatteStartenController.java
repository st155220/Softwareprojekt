package de.hohenheim.realdemocracy.controller;

import de.hohenheim.realdemocracy.entity.*;
import de.hohenheim.realdemocracy.service.DebatteService;
import de.hohenheim.realdemocracy.service.HelpService;
import de.hohenheim.realdemocracy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DebatteStartenController {

    @Autowired
    private UserService userService;
    @Autowired
    private DebatteService debatteService;
    @Autowired
    private HelpService helpService;

    @GetMapping("/debatteStarten")
    public String showDebatteStarten(Model model) {
        if (!(userService.getCurrentUser() instanceof Politician)) {
            model.addAttribute("debatten", helpService.getDebattes());
            model.addAttribute("username", helpService.getCurrentUsername());
            return "home";
        }
        return "debatteStarten";
    }

    @PostMapping("/debatteStarten/home")
    public String debatteStarten(HttpServletRequest req, Model model) {
        String titel = req.getParameter("titel");
        String problemstellung = req.getParameter("problemstellung");
        String loesungsstrategie = req.getParameter("loesungsstrategie");
        String stichtag = req.getParameter("stichtag");
        Bundesland bundesland = helpService.getBundesland(req.getParameter("bundesland"));

        if (titel.equals("") || problemstellung.equals("") || loesungsstrategie.equals("") || stichtag.equals("")) {
            return "debatteStarten";
        }

        Debatte newDebatte = new Debatte();
        newDebatte.setErsteller((Politician) userService.getCurrentUser());
        newDebatte.setBundesland(bundesland);
        newDebatte.setTitel(titel);
        newDebatte.setProblemstellung(problemstellung);
        newDebatte.setLoesungsstrategie(loesungsstrategie);
        newDebatte.setStichtag(stichtag);
        debatteService.saveDebatte(newDebatte);

        model.addAttribute("debatten", helpService.getDebattes());
        model.addAttribute("username", helpService.getCurrentUsername());
        return "home";
    }

}
