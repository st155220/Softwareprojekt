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
public class ConfirmEmailController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailSenderService emailSenderService;

    @GetMapping("/confirmEmail")
    public String showConfirmEmail() {
        return "confirmEmail";
    }

    @PostMapping("/confirmEmail/login")
    public String confirmEmail(HttpServletRequest req, Model model) {
        String code = req.getParameter("code");
        User user = userService.getTempUser();
        if (code.equals(userService.getEmailConfirmationCode())) {
            user.setEmailConfirmed(true);
            userService.saveUser(user);
            model.addAttribute("conSuccessful", true);
            return "login";
        } else {
            emailSenderService.sendCode(user);
            model.addAttribute("failed", true);
            return "confirmEmail";
        }
    }

    @PostMapping("/confirmEmail/otherEmail")
    public String otherEmail(HttpServletRequest req, Model model) {
        String email = req.getParameter("email");
        User user = userService.getTempUser();
        if (!userService.isCorrectEmail(email)) {
            emailSenderService.sendCode(user);
            model.addAttribute("failed", true);
            return "confirmEmail";
        }
        user.setEmail(email);
        userService.saveUser(user);
        emailSenderService.sendCode(user);
        model.addAttribute("otherEmail", true);
        return "confirmEmail";
    }
}