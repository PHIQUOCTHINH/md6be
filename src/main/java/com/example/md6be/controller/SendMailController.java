package com.example.md6be.controller;

import com.example.md6be.model.AppUser;
import com.example.md6be.service.impl.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/email")
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
                + "Trưa nay ăn gì! xin thông báo, vào ngày " + day
                + " bạn đã chính thức trở thành một thành viên tham gia Ứng dụng Trưa nay ăn gì!"
                + " cùng chung tay xây dựng Trưa nay ăn gì! ngày càng phát triển nhé! \n"
                + "Mọi thắc mắc xin liên hệ: \n"
                + "Hotline: 0888 666 888 \n"
                + "Email: dthang277@gmail.com.com");

        //send message
        this.emailSender.send(message);

    }
}

