package com.skillball.controller;

import com.skillball.entity.User;
import com.skillball.service.EmailSenderService;
import com.skillball.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
//
@Controller
public class ChangePasswordController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/changePassword")
    public String showChangePassword() {
        return "changePassword";
    }

    @PostMapping("/changePassword/login")
    public String changePassword(HttpServletRequest req, Model model) {
        String code = req.getParameter("code");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");
        User user = userService.getTempUser();
        if (code.equals(userService.getEmailConfirmationCode()) && newPassword.equals(confirmPassword)) {
            userService.changePassword(user.getUserId(), passwordEncoder.encode(newPassword));
            model.addAttribute("successful", true);
            return "login";
        } else {
            emailSenderService.sendCode(user);
            model.addAttribute("failed", true);
            return "changePassword";
        }
    }
}