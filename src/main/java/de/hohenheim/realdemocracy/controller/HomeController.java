package de.hohenheim.realdemocracy.controller;

import de.hohenheim.realdemocracy.entity.Debatte;
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

}
