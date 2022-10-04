package com.example.md6be.controller;

import com.example.md6be.model.AppUser;
import com.example.md6be.model.Customer;
import com.example.md6be.model.Merchant;
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
    // send mail cho merchant
    public void sendEmail(AppUser appUser) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dayPlusAWeek = LocalDate.now().plusDays(0);
        String day = formatter.format(dayPlusAWeek);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(appUser.getUsername());
        message.setSubject("Thông báo đăng ký thành công "  + day);
        message.setText("Xin chào \n" + appUser.getUsername()
                + "Bạn có thể đăng sản phẩm lên trang web");

        //send message
        this.emailSender.send(message);

    }
    public void sendEmailBan(Merchant merchant) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dayPlusAWeek = LocalDate.now().plusDays(0);
        String day = formatter.format(dayPlusAWeek);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(merchant.getAppUser().getUsername());
        if (!merchant.getIsActive()){
            message.setSubject("Thông báo tài khoản đã bị ban" + day);

            //send message
        } else {
            message.setSubject("Thông báo tài khoản đã đã được mở" + day);

            //send message
        }
        message.setText("Xin chào \n"
                + "Email: okanemochininaru98@gmail.com");
        this.emailSender.send(message);

    }
    //send mail cho customer
    public void sendEmailC(AppUser appUser) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dayPlusAWeek = LocalDate.now().plusDays(0);
        String day = formatter.format(dayPlusAWeek);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(appUser.getUsername());
        message.setSubject("Thông báo đăng ký thành công" + day);
        message.setText("Xin chào \n" + appUser.getUsername()
                + "Bạn đã trờ thành thành viên");

        //send message
        this.emailSender.send(message);

    }
    public void sendEmailBanCustomer(Customer customer) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dayPlusAWeek = LocalDate.now().plusDays(0);
        String day = formatter.format(dayPlusAWeek);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(customer.getAppUser().getUsername());
        if (!customer.getIsActive()){
            message.setSubject("Thông báo tài khoản đã bị ban" + day);

            //send message
        } else {
            message.setSubject("Thông báo tài khoản đã đã được mở" + day);

            //send message
        }
        message.setText("Xin chào \n"
                + "Email: okanemochininaru98@gmail.com");
        this.emailSender.send(message);

    }
}