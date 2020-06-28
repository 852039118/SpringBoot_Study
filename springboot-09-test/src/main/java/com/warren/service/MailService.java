package com.warren.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

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

    // 邮件测试
    public void mailTest() throws MessagingException {
        //邮件设置2：一个复杂的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        Date date = new Date();
        helper.setSubject(date.toLocaleString()+"这将是美好的一天！");

        helper.setText("<div style=\"background-image:linear-gradient(to right bottom, rgb(254,166,255), #bada55)\">\n" +
                "    <div style=\"text-align: left;color: rgb(60,112,255); font-family: 楷体, 楷体_GB2312; font-size: 1.25rem;\">\n" +
                "        <b><i>\n" +
                "            光洒进暖流, 花开在枝上, 春光正好, 我把我种在你身体里, 然后一起躲进时间的褶皱里。\n" +
                "        </i></b>\n" +
                "    </div>\n" +
                "    <div style=\"text-align: right;color: rgb(60,112,255); font-family: 楷体, 楷体_GB2312; font-size: 1.25rem;\">\n" +
                "        <i>\n" +
                "            <b>\n" +
                "                by 渡海平山\n" +
                "            </b>\n" +
                "            <b>\n" +
                "                《\n" +
                "            </b>\n" +
                "        </i>\n" +
                "        <b><i>\n" +
                "            On My Head\n" +
                "        </i></b>\n" +
                "        <b><i>\n" +
                "            》\n" +
                "        </i></b>\n" +
                "    </div>\n" +
                "\n" +
                "\n" +
                "</div>\n" +
                "<br>\n" +
                "<div style=\"background-image:linear-gradient(to right bottom, #bada55, rgb(254,166,255))\">\n" +
                "    <div style=\"text-align: center;color: rgb(60,112,255); font-family: 楷体, 楷体_GB2312; font-size: 1.25rem;\">\n" +
                "        <b><i>\n" +
                "            歌舞尊前，繁华镜里，暗换青青发。\n" +
                "        </i></b>\n" +
                "    </div>\n" +
                "</div>\n" +
                "<br>\n" +
                "<div style=\"background-image:linear-gradient(to right bottom, rgb(254,166,255), #bada55)\">\n" +
                "    <div style=\"text-align: center;color: rgb(60,112,255); font-family: 楷体, 楷体_GB2312; font-size: 1.25rem;\">\n" +
                "        <p>\n" +
                "            <b><i>\n" +
                "                Sometimes a little discomfort in the beginning can save a whole lot of pain down the road.\n" +
                "            </i></b>\n" +
                "        </p>\n" +
                "\n" +
                "        <b><i>\n" +
                "            有时起初的隐忍可以避免一路的疼痛。\n" +
                "        </i></b>\n" +
                "    </div>\n" +
                "</div>",true);
        //发送附件
//        helper.addAttachment("1.jpg",new File("C:\\Users\\85203\\Desktop\\smile.png"));

        helper.setFrom("997194702@qq.com");    // 发件人
        helper.setTo("852039118@qq.com");      // 收件人

        mailSender.send(mimeMessage);
    }


}
