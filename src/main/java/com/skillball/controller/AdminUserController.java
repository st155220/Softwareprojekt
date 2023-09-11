package com.skillball.controller;

import com.skillball.service.RoleService;
import com.skillball.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
//
@Controller
public class AdminUserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/admin/adminUser")
    public String showAdminUser(Model model) {
        model.addAttribute("userList", userService.listUsers());
        return "adminUser";
    }

    @PostMapping("/admin/editUser")
    public String editUser(HttpServletRequest req, Model model) {
        String username = req.getParameter("username");
        String confirmUsername = req.getParameter("confirmUsername");
        String button = req.getParameter("button");
        if (userService.existsByUsername(username) && !userService.getCurrentUser().getUsername().equals(username)
                && username.equals(confirmUsername)) {
            if (button.equals("selectAdmin")) {
                userService.setRole(userService.getUserByUsername(username), roleService.getRoleByRolename("ROLE_ADMIN"));
            } else {
                userService.deleteUser(userService.getUserByUsername(username));
            }
            model.addAttribute("successful", true);
        } else {
            model.addAttribute("failed", true);
        }
        model.addAttribute("userList", userService.listUsers());
        return "adminUser";
    }
}