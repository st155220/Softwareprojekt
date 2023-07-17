package com.skillball.controller;

import com.skillball.service.EmailSenderService;
import com.skillball.entity.User;
import com.skillball.service.RoleService;
import com.skillball.service.UserService;
import com.skillball.service.VocabService;
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
public class AdminLoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private VocabService vocabService;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/adminLogin")
    public String showAdminLogin() {
        return "adminLogin";
    }

    @PostMapping("/adminLogin/adminVocab")
    public String adminLogin(HttpServletRequest req, Model model) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = userService.getUserByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())
                || !user.getRoles().contains(roleService.getRoleByRolename("ROLE_ADMIN"))) {
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
        vocabService.setCurrentVocabList(null, true);
        model.addAttribute("successful", true);
        return "adminVocab";
    }
}