package com.warren.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailService {
    @Autowired
    JavaMailSenderImpl mailSender;

    private MimeMessage mimeMessage;
    private MimeMessageHelper helper;

    public void init() throws MessagingException {
        mimeMessage = mailSender.createMimeMessage();
        helper = new MimeMessageHelper(mimeMessage,true);
    }

    /*
    * 发件人：from
    * 收件人：to
    * 主题：subject
    * 内容：content
    * 是否解析为HTML：bHtml
    *
    * */
    public void sendMail(String from,String to,String subject,String content,boolean bHtml) throws MessagingException {
        this.init();

        helper.setSubject(subject);

        helper.setText(content,bHtml);

        helper.setFrom(from);    // 发件人
        helper.setTo(to);      // 收件人

        mailSender.send(mimeMessage);
    }

    /*
    * 附件显示名称：addAttachmentName
    * 附件本地位置：addAttachmentFile
    * */
    public void sendMail(String from,String to,String subject,String content,boolean bHtml,String addAttachmentName,String addAttachmentFile) throws MessagingException {

        helper.addAttachment(addAttachmentName,new File(addAttachmentFile));

        this.sendMail(from,to,subject,content,bHtml);
    }
}
