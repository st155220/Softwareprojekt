package com.skillball.controller;

import com.skillball.entity.User;
import com.skillball.service.RoleService;
import com.skillball.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    @PostMapping("/register/login")
    public String register(HttpServletRequest req, Model model) {
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String agb = req.getParameter("agb");
        if (!userService.isCorrectEmail(email)) {
            model.addAttribute("failed", true);
            return "register";
        }
        if (username.length() < 3 || password.length() < 3 || agb == null || !password.equals(confirmPassword)) {
            model.addAttribute("failed", true);
            return "register";
        }
        if (userService.existsByEmail(email) || userService.existsByUsername(username)) {
            model.addAttribute("failed", true);
            return "register";
        }
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setLanguage("German");
        newUser.setLevel("Basic");
        newUser.setIndex(1);
        newUser.setDifficulty("Normal");
        newUser.setDurationQuarter(180);
        newUser.setRsLength(7);
        newUser.setRole(roleService.getRoleByRolename("ROLE_USER"));
        newUser.setEmailConfirmed(false);
        newUser.setEnabled(true);
        userService.saveUser(newUser);
        model.addAttribute("regSuccessful", true);
        return "login";
    }
}