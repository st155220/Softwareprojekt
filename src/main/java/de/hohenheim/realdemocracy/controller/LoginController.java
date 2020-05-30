package de.hohenheim.realdemocracy.controller;

import de.hohenheim.realdemocracy.entity.Sektor;
import de.hohenheim.realdemocracy.entity.User;
import de.hohenheim.realdemocracy.service.HelpService;
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

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private HelpService helpService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String startRealDemocracy() {
        return "login";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login/home")
    public String login(HttpServletRequest req, Model model) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(username, password);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authReq);

        User user = userService.getUserByUsername(username);

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            return "login";
        }

        HomeController.currentSektor = Sektor.Alle;
        model.addAttribute("debatten", helpService.getDebattes());
        model.addAttribute("username", userService.getCurrentUser().getUsername());
        return "home";
    }
}