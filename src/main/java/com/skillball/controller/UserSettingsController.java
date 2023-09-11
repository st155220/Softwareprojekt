package com.skillball.controller;

import com.skillball.entity.User;
import com.skillball.service.UserService;
import com.skillball.service.VocabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
//
@Controller
public class UserSettingsController {

    @Autowired
    private UserService userService;
    @Autowired
    private VocabService vocabService;

    @GetMapping("/userHome/userSettings")
    public String showUserSettings(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("difficulty", user.getDifficulty());
        model.addAttribute("durationQuarter", user.getDurationQuarter());
        model.addAttribute("rsLength", user.getRsLength());
        model.addAttribute("title", user.getLanguage() + " " + user.getLevel() + " " + user.getIndex());
        return "userSettings";
    }

    @PostMapping("/userHome/vocabList")
    public String vocabList(HttpServletRequest req, Model model) {
        User user = userService.getCurrentUser();
        String button = req.getParameter("button");
        if (button.equals("view")) {
            vocabService.updateCurrentVocabList(user.getLanguage(), user.getLevel(), user.getIndex(), false);
            model.addAttribute("title", user.getLanguage() + " " + user.getLevel() + " " + user.getIndex());
            model.addAttribute("templateList", vocabService.createTemplateList(false));
            model.addAttribute("inGame", false);
            return "userVocab";
        } else {
            String language = req.getParameter("language");
            String level = req.getParameter("level");
            int index = Integer.parseInt(req.getParameter("index"));
            user.setLanguage(language);
            user.setLevel(level);
            user.setIndex(index);
            userService.saveUser(user);
            model.addAttribute("difficulty", user.getDifficulty());
            model.addAttribute("durationQuarter", user.getDurationQuarter());
            model.addAttribute("rsLength", user.getRsLength());
            model.addAttribute("title", user.getLanguage() + " " + user.getLevel() + " " + user.getIndex());
            return "userSettings";
        }
    }

    @PostMapping("/userVocab/userHome")
    public String userVocab(HttpServletRequest req, Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("difficulty", user.getDifficulty());
        model.addAttribute("durationQuarter", user.getDurationQuarter());
        model.addAttribute("rsLength", user.getRsLength());
        model.addAttribute("title", user.getLanguage() + " " + user.getLevel() + " " + user.getIndex());
        return "userSettings";
    }

    @PostMapping("/userHome/difficulty")
    public String difficulty(HttpServletRequest req, Model model) {
        User user = userService.getCurrentUser();
        String difficulty = req.getParameter("difficulty");
        user.setDifficulty(difficulty);
        userService.saveUser(user);
        model.addAttribute("difficulty", user.getDifficulty());
        model.addAttribute("durationQuarter", user.getDurationQuarter());
        model.addAttribute("rsLength", user.getRsLength());
        model.addAttribute("title", user.getLanguage() + " " + user.getLevel() + " " + user.getIndex());
        return "userSettings";
    }

    @PostMapping("/userHome/durationQuarter")
    public String durationQuarter(HttpServletRequest req, Model model) {
        User user = userService.getCurrentUser();
        int durationQuarter = Integer.parseInt(req.getParameter("durationQuarter"));
        user.setDurationQuarter(durationQuarter);
        userService.saveUser(user);
        model.addAttribute("difficulty", user.getDifficulty());
        model.addAttribute("durationQuarter", user.getDurationQuarter());
        model.addAttribute("rsLength", user.getRsLength());
        model.addAttribute("title", user.getLanguage() + " " + user.getLevel() + " " + user.getIndex());
        return "userSettings";
    }

    @PostMapping("/userHome/rsLength")
    public String rsLength(HttpServletRequest req, Model model) {
        User user = userService.getCurrentUser();
        int rsLength = Integer.parseInt(req.getParameter("rsLength"));
        user.setRsLength(rsLength);
        userService.saveUser(user);
        model.addAttribute("difficulty", user.getDifficulty());
        model.addAttribute("durationQuarter", user.getDurationQuarter());
        model.addAttribute("rsLength", user.getRsLength());
        model.addAttribute("title", user.getLanguage() + " " + user.getLevel() + " " + user.getIndex());
        return "userSettings";
    }
}