package com.example.md6be.controller;

import com.example.md6be.model.AppUser;
import com.example.md6be.service.impl.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@EnableScheduling
public class SendMailController {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    AppUserService appUserService;

    public void sendEmail(AppUser appUser) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dayPlusAWeek = LocalDate.now().plusDays(0);
        String day = formatter.format(dayPlusAWeek);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(appUser.getUsername());
        message.setSubject("Thông báo đăng ký thành công");
        message.setText("Chào bạn \n"
                + "Email: okanemochininaru98@gmail.com");

        //send message
        this.emailSender.send(message);

    }
}
