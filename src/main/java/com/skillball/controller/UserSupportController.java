package com.skillball.controller;

import com.skillball.entity.Ticket;
import com.skillball.entity.User;
import com.skillball.service.TicketService;
import com.skillball.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
//
@Controller
public class UserSupportController {

    @Autowired
    private UserService userService;
    @Autowired
    private TicketService ticketService;

    @GetMapping("/userHome/userSupport")
    public String showUserSupport(Model model) {
        model.addAttribute("ticketList", ticketService.listRelevantTickets(userService.getCurrentUser()));
        return "userSupport";
    }

    @PostMapping("/userHome/sendTicket")
    public String sendTicket(HttpServletRequest req, Model model) {
        User user = userService.getCurrentUser();
        String title = req.getParameter("title");
        String text = req.getParameter("text");
        if (title.length() < 3 || text.length() < 3) {
            model.addAttribute("ticketList", ticketService.listRelevantTickets(user));
            model.addAttribute("failed", true);
            return "userSupport";
        }
        Ticket ticket = new Ticket();
        ticket.setRequester(user);
        ticket.setTimeStamp();
        ticket.setTitle(title);
        ticket.setText(text);
        ticketService.saveTicket(ticket);
        model.addAttribute("ticketList", ticketService.listRelevantTickets(user));
        model.addAttribute("successful", true);
        return "userSupport";
    }

    @PostMapping("/userHome/deleteTicket")
    public String deleteTicket(HttpServletRequest req, Model model) {
        String id = req.getParameter("button");
        int ticketId = Integer.parseInt(id);
        Ticket ticket = ticketService.getTicketByTicketId(ticketId);
        if (ticket.isDeletedByAdmin()) {
            ticketService.deleteTicket(ticket);
        } else {
            ticket.setDeletedByUser(true);
            ticketService.saveTicket(ticket);
        }
        model.addAttribute("ticketList", ticketService.listRelevantTickets(userService.getCurrentUser()));
        return "userSupport";
    }
}