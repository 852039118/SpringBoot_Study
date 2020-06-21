package com.warren;

import com.warren.service.ScheduledService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot09TestApplicationTests {
    @Autowired
    JavaMailSenderImpl mailSender;
    @Autowired
    ScheduledService scheduledService;

    @Test
    void contextLoads() {
        //邮件设置1：一个简单的邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setSubject("通知-今天 你学习了吗");  // 主题
        mailMessage.setText("今晚 猎个痛快");     // 正文

        mailMessage.setFrom("852039118@qq.com");    // 发件人
        mailMessage.setTo("997194702@qq.com");      // 收件人

        mailSender.send(mailMessage);
    }

    @Test
    void contextLoads2() throws MessagingException {
        //邮件设置2：一个复杂的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

        helper.setSubject("通知-今天 你学习了吗");

        helper.setText("<h4 style='color:pink'>侬本多情(Live)</h4>" +
                            "<p>情爱就好似一串梦</p>" +
                            "<p>梦醒了一切亦空</p>" +
                            "<p>或者是我天生多情</p>" +
                            "<p>方给爱情戏弄</p>",true);
        //发送附件
        helper.addAttachment("1.jpg",new File("C:\\Users\\85203\\Desktop\\smile.png"));

        helper.setFrom("852039118@qq.com");    // 发件人
        helper.setTo("997194702@qq.com");      // 收件人

        mailSender.send(mimeMessage);
    }


}
