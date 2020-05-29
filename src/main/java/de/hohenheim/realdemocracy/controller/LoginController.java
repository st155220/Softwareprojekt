package de.hohenheim.realdemocracy.controller;

import de.hohenheim.realdemocracy.config.SecurityConfiguration;
import de.hohenheim.realdemocracy.entity.Debatte;
import de.hohenheim.realdemocracy.entity.User;
import de.hohenheim.realdemocracy.service.DebatteService;
import de.hohenheim.realdemocracy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private DebatteService debatteService;
    @Autowired
    private SecurityConfiguration securityConfiguration;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String start() {
        return "login";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login/home")
    public String logIn(HttpServletRequest req, Model model) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(username, password);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authReq);

        User user = userService.getUserByUsername(username);
        if(user == null){
            return "login";
        }

        if (passwordEncoder.matches(password, user.getPassword())) {
            List<Debatte> debatten = debatteService.find_All_Debates();
            model.addAttribute("debatten", debatten);
            model.addAttribute("username", username);
            return "home";
        }
        return "login";
    }
}