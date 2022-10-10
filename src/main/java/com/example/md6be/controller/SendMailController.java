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
    public void sendEmail(Merchant merchant) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dayPlusAWeek = LocalDate.now().plusDays(0);
        String day = formatter.format(dayPlusAWeek);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(merchant.getAppUser().getUsername());
        message.setSubject("Thông báo đăng ký thành công "  + day);
        message.setText("Chào bạn " +"Hãy nhấn vào link dưới đây để kich hoạt tài khoản" +"http://localhost:8080/api/admin/accept-merchant/"+ merchant.getId() + "\n"

                + "Trưa nay ăn gì! xin thông báo, vào ngày " + day
                + " bạn đã chính thức trở thành một thành viên tham gia Ứng dụng Trưa nay ăn gì!"
                + " cùng chung tay xây dựng Trưa nay ăn gì! ngày càng phát triển nhé! \n"
                + "Mọi thắc mắc xin liên hệ: \n"
                + "Hotline: 0888 666 888 \n"
                + "Email: okanemochininaru98@gmail.com.com"
        );

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
    public void sendEmailC(Customer customer) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dayPlusAWeek = LocalDate.now().plusDays(0);
        String day = formatter.format(dayPlusAWeek);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(customer.getAppUser().getUsername());
        message.setSubject("Thông báo đăng ký thành công" + day);
        message.setText("Chào bạn " +"Hãy nhấn vào link dưới đây để kich hoạt tài khoản" +"http://localhost:8080/api/admin/accept-merchant/"+ customer.getId() + "\n"

                + "Trưa nay ăn gì! xin thông báo, vào ngày " + day
                + " bạn đã chính thức trở thành một thành viên tham gia Ứng dụng Trưa nay ăn gì!"
                + " cùng chung tay xây dựng Trưa nay ăn gì! ngày càng phát triển nhé! \n"
                + "Mọi thắc mắc xin liên hệ: \n"
                + "Hotline: 0888 666 888 \n"
                + "Email: okanemochininaru98@gmail.com.com"
        );

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
