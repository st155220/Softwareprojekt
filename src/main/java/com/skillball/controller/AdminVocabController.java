package com.skillball.controller;

import com.skillball.entity.User;
import com.skillball.entity.Vocab;
import com.skillball.service.TicketService;
import com.skillball.service.UserService;
import com.skillball.service.VocabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminVocabController {

    @Autowired
    private UserService userService;
    @Autowired
    private VocabService vocabService;
    @Autowired
    private TicketService ticketService;

    @GetMapping("/admin/adminVocab")
    public String showAdminVocab() {
        return "adminVocab";
    }

    @PostMapping("/admin/sites")
    public String sites(HttpServletRequest req, Model model) {
        User user = userService.getCurrentUser();
        String site = req.getParameter("site");
        if (site.equals("Vocab")) {
            vocabService.setCurrentVocabList(null, true);
            return "adminVocab";
        } else if (site.equals("User")) {
            model.addAttribute("userList", userService.listUsers());
            return "adminUser";
        } else if (site.equals("Account")) {
            model.addAttribute("email", "E-Mail: " + user.getEmail());
            model.addAttribute("username", "Username: " + user.getUsername());
            return "adminAccount";
        } else {
            model.addAttribute("ticketList", ticketService.listRelevantTickets());
            return "adminSupport";
        }
    }

    @PostMapping("/admin/selectList")
    public String selectList(HttpServletRequest req, Model model) {
        String language = req.getParameter("language");
        String level = req.getParameter("level");
        int index = Integer.parseInt(req.getParameter("index"));
        vocabService.updateCurrentVocabList(language, level, index, true);
        model.addAttribute("title", language + " " + level + " " + index);
        model.addAttribute("templateList", vocabService.createTemplateList(true));
        return "adminVocab";
    }

    @PostMapping("/admin/addVocab")
    public String addVocab(HttpServletRequest req, Model model) {
        String english = req.getParameter("english");
        String translation = req.getParameter("translation");
        List<Vocab> vocabList = vocabService.getCurrentVocabList(true);
        if (vocabList == null) {
            return "adminVocab";
        }
        Vocab exVocab = vocabList.get(vocabList.size() - 1);
        Vocab vocab = new Vocab();
        vocab.setLanguage(exVocab.getLanguage());
        vocab.setLevel(exVocab.getLevel());
        vocab.setIndex(exVocab.getIndex());
        vocab.setEnglish(english);
        vocab.setTranslation(translation);
        vocab.setPosition(exVocab.getPosition() + 1);
        vocabService.saveVocab(vocab);
        vocabService.updateCurrentVocabList(vocab.getLanguage(), vocab.getLevel(), vocab.getIndex(), true);
        model.addAttribute("title", vocab.getLanguage() + " " + vocab.getLevel() + " " + vocab.getIndex());
        model.addAttribute("templateList", vocabService.createTemplateList(true));
        return "adminVocab";
    }

    @PostMapping("/admin/removeVocab")
    public String removeVocab(HttpServletRequest req, Model model) {
        List<Vocab> vocabList = vocabService.getCurrentVocabList(true);
        if (vocabList == null) {
            return "adminVocab";
        }
        Vocab vocab = vocabList.get(0);
        if (!req.getParameter("position").equals("") && Integer.parseInt(req.getParameter("position")) >= 1
                && Integer.parseInt(req.getParameter("position")) <= vocabList.size()) {
            int position = Integer.parseInt(req.getParameter("position"));
            vocab = vocabList.get(position - 1);
            vocabService.deleteVocab(vocab);
            for (int i = position; i < vocabList.size(); i++) {
                vocab = vocabList.get(i);
                vocab.setPosition(vocab.getPosition() - 1);
                vocabService.saveVocab(vocab);
            }
        }
        vocabService.updateCurrentVocabList(vocab.getLanguage(), vocab.getLevel(), vocab.getIndex(), true);
        model.addAttribute("title", vocab.getLanguage() + " " + vocab.getLevel() + " " + vocab.getIndex());
        model.addAttribute("templateList", vocabService.createTemplateList(true));
        return "adminVocab";
    }
}