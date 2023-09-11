package com.skillball.controller;

import com.skillball.service.EmailSenderService;
import com.skillball.entity.User;
import com.skillball.service.GameService;
import com.skillball.service.RoleService;
import com.skillball.service.UserService;
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
//
@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private GameService gameService;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String startSkillball() {
        return "login";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login/userHome")
    public String login(HttpServletRequest req, Model model) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = userService.getUserByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())
                || !user.getRoles().contains(roleService.getRoleByRolename("ROLE_USER"))) {
            model.addAttribute("failed", true);
            return "login";
        }
        if (!user.isEmailConfirmed()) {
            emailSenderService.sendCode(user);
            return "confirmEmail";
        }
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(username, password);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authReq);
        model.addAttribute("welcome", "Hello " + user.getUsername() + "! Nice that You are here!");
        model.addAttribute("games", gameService.listRelevantGames(user));
        model.addAttribute("successful", true);
        return "userHome";
    }
}