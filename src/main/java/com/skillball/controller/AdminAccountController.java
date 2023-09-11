package com.skillball.controller;

import com.skillball.entity.User;
import com.skillball.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminAccountController {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/admin/adminAccount")
    public String showAdminAccount() {
        return "adminAccount";
    }

    @PostMapping("/admin/changeEmail")
    public String changeEmail(HttpServletRequest req, Model model) {
        String newEmail = req.getParameter("newEmail");
        User user = userService.getCurrentUser();
        if (!userService.isCorrectEmail(newEmail) || userService.existsByEmail(newEmail)) {
            model.addAttribute("email", "E-Mail: " + user.getEmail());
            model.addAttribute("username", "Username: " + user.getUsername());
            model.addAttribute("failed", true);
            return "adminAccount";
        }
        userService.changeEmail(user.getUserId(), newEmail);
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        model.addAttribute("email", "E-Mail: " + user.getEmail());
        model.addAttribute("username", "Username: " + user.getUsername());
        model.addAttribute("successful", true);
        return "adminAccount";
    }

    @PostMapping("/admin/changeUsername")
    public String changeUsername(HttpServletRequest req, Model model) {
        String newUsername = req.getParameter("newUsername");
        User user = userService.getCurrentUser();
        if (newUsername.length() < 3 || userService.existsByUsername(newUsername)) {
            model.addAttribute("email", "E-Mail: " + user.getEmail());
            model.addAttribute("username", "Username: " + user.getUsername());
            model.addAttribute("failed", true);
            return "adminAccount";
        }
        userService.changeUsername(user.getUserId(), newUsername);
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        model.addAttribute("email", "E-Mail: " + user.getEmail());
        model.addAttribute("username", "Username: " + user.getUsername());
        model.addAttribute("successful", true);
        return "adminAccount";
    }

    @PostMapping("/admin/changePassword")
    public String changePassword(HttpServletRequest req, Model model) {
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");
        User user = userService.getCurrentUser();
        if (newPassword.length() < 3 || !passwordEncoder.matches(oldPassword, user.getPassword())
                || !newPassword.equals(confirmPassword)) {
            model.addAttribute("email", "E-Mail: " + user.getEmail());
            model.addAttribute("username", "Username: " + user.getUsername());
            model.addAttribute("failed", true);
            return "adminAccount";
        }
        userService.changePassword(user.getUserId(), passwordEncoder.encode(newPassword));
        model.addAttribute("email", "E-Mail: " + user.getEmail());
        model.addAttribute("username", "Username: " + user.getUsername());
        model.addAttribute("successful", true);
        return "adminAccount";
    }
}