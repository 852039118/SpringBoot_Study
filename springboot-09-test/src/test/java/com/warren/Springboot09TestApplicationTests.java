package com.warren;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.warren.api.GetExternalApi;
import com.warren.service.MailService;
import com.warren.service.ScheduledService;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class Springboot09TestApplicationTests {
 /*   @Autowired
    JavaMailSenderImpl mailSender;
    @Autowired
    ScheduledService scheduledService;
    @Autowired
    MailService mailService;
    @Autowired
    GetExternalApi verseApi;

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
//        helper.addAttachment("1.jpg",new File("C:\\Users\\85203\\Desktop\\smile.png"));

        helper.setFrom("852039118@qq.com");    // 发件人
        helper.setTo("997194702@qq.com");      // 收件人

        mailSender.send(mimeMessage);
    }

    @Test
    void MyTest() throws MessagingException {
        Date date = new Date();
        System.out.println(date.toLocaleString());
        mailService.mailTest();
    }

    // 今日网易云音乐热评
    @Test
    void testHttp() throws JSONException {
        JSON netMusicContent = verseApi.getNetMusicContent("http://localhost:3000/comment/hotwall/list");
        System.out.println(netMusicContent);
    }

    // 今日诗词
    @Test
    void testVerse(){
        verseApi.getData("https://v1.jinrishici.com/rensheng.txt");
    }

    // 词霸 每日一句
    @Test
    void testEnglishEveryday(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        JSONObject powerWord = verseApi.getPowerWord("http://sentence.iciba.com/index.php?c=dailysentence&m=getdetail&title=" + date);
        // 获取返回来的值
        String content = powerWord.getString("content");
        String note = powerWord.getString("note");
        System.out.println(content);
        System.out.println(note);
    }*/
}
