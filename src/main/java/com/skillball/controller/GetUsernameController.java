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
public class GetUsernameController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailSenderService emailSenderService;

    @GetMapping("/getUsername")
    public String showGetUsername() {
        return "getUsername";
    }

    @PostMapping("/getUsername/login")
    public String getUsername(HttpServletRequest req, Model model) {
        String email = req.getParameter("email");
        User user = userService.getUserByEmail(email);
        if (user == null) {
            model.addAttribute("failed", true);
            return "getUsername";
        }
        emailSenderService.sendEmail(email, "Skillball - Username",
                "Hello,\n\nYour Username is:\n\n" + user.getUsername());
        model.addAttribute("emailSuccessful", true);
        return "login";
    }
}