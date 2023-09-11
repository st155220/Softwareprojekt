package com.skillball.service;

import com.skillball.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
//
@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserService userService;

    public void sendEmail(String toEmail, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dominik.larche@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        text += "\n\n\n\nKind regards\n\nYour Skillball Team\n\nSkillball Foundation 2023";
        message.setText(text);
        javaMailSender.send(message);
    }

    public void sendCode(User user) {
        String code = "";
        for (int i = 0; i < 6; i++) {
            code += (int) (Math.random() * 10);
        }
        userService.setEmailConfirmationCode(code);
        userService.setTempUser(user);
        sendEmail(user.getEmail(), "Skillball - Confirm Email",
                "Hello,\n\nYour Confirmation Code is:\n\n" + code);
    }
}