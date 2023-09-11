package com.skillball.controller;

import com.skillball.service.EmailSenderService;
import com.skillball.entity.User;
import com.skillball.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
//
@Controller
public class PasswordController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailSenderService emailSenderService;

    @GetMapping("/password")
    public String showPassword() {
        return "password";
    }

    @PostMapping("/password/changePassword")
    public String changePassword(HttpServletRequest req, Model model) {
        String email = req.getParameter("email");
        User user = userService.getUserByEmail(email);
        if (user == null) {
            model.addAttribute("failed", true);
            return "password";
        }
        String code = "";
        for (int i = 0; i < 6; i++) {
            code += (int) (Math.random() * 10);
        }
        userService.setEmailConfirmationCode(code);
        userService.setTempUser(user);
        emailSenderService.sendEmail(user.getEmail(), "Skillball - Confirm Email",
                "Hello,\n\nYour Confirmation Code is:\n\n" + code);
        return "changePassword";
    }
}