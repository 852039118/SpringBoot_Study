package com.warren.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.warren.api.GetExternalApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ScheduledService {
    String from = "997194702@qq.com", to = "852039118@qq.com", subject = "",content="";

    @Autowired
    MailService mailService;
    @Autowired
    GetExternalApi everyData;

    // 初始化每日数据
    void initEveryday(){
        subject = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " Happy~";
        // 调用接口获取今日返回的JSON对象数据
        JSONObject music = everyData.getNetMusicContent();
        String verse = everyData.getData();
        JSONObject powerWord = everyData.getPowerWord();
        // 网易云音乐评论数据
        String name = music.getString("name");
        String musicContent = music.getString("content");
        String nickname = music.getString("nickname");
        String avatar = music.getString("avatar");
        // 词霸 每日一句
        String wordContent = powerWord.getString("content");
        String note = powerWord.getString("note");
        // 组装 邮件HTML
        content = "<div style=\"background-image:linear-gradient(to right bottom, rgb(254,166,255), #bada55)\">\n" +
                "    <div style=\"text-align: left;color: rgb(60,112,255); font-family: 楷体, 楷体_GB2312; font-size: 1.25rem;\">\n" +
                "        <b><i>\n" +
                musicContent +
                "        </i></b>\n" +
                "    </div>\n" +
                "    <div style=\"text-align: right;color: rgb(60,112,255); font-family: 楷体, 楷体_GB2312; font-size: 1.25rem;\">\n" +
                "        <i>\n" +
                "            <b>\n" +
                "                by "+ nickname +
                "            </b>\n" +
                "            <b>\n" +
                "                《\n" +
                "            </b>\n" +
                "        </i>\n" +
                "        <b><i>\n" +
                name +
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
                verse +
                "        </i></b>\n" +
                "    </div>\n" +
                "</div>\n" +
                "<br>\n" +
                "<div style=\"background-image:linear-gradient(to right bottom, rgb(254,166,255), #bada55)\">\n" +
                "    <div style=\"text-align: center;color: rgb(60,112,255); font-family: 楷体, 楷体_GB2312; font-size: 1.25rem;\">\n" +
                "        <p>\n" +
                "            <b><i>\n" +
                wordContent +
                "            </i></b>\n" +
                "        </p>\n" +
                "        <b><i>\n" +
                note +
                "        </i></b>\n" +
                "    </div>\n" +
                "</div>";
    }

    // Cron表达式
    // 秒 分 时 日 月 星期 年
    // 在一个特定的和时间执行这个方法~ Timer5
/*    @Scheduled(cron = "0 0 9 * * ?")
    public void morning() throws MessagingException {
        this.initEveryday();
        mailService.sendMail(from,to,subject,content, true);
    }*/

    @Scheduled(cron = "0 0 23 * * ?")
    public void evening() throws MessagingException {
        this.initEveryday();
        mailService.sendMail(from,"1377818125@qq.com",subject,content, true);
        mailService.sendMail(from,to,subject,content, true);
    }

    /*@Scheduled(cron = "0/10 * * * * ?")
    public void scheduledTest() throws MessagingException {
//        System.out.println("每隔10秒发送一次");
        this.initEveryday();
        mailService.sendMail(from,to,subject,content, true);
    }*/

    @Scheduled(cron = "* 0/30 * 28 6 ?")
    public void scheduledTest1() throws MessagingException {
//        System.out.println("每隔10秒发送一次");
        this.initEveryday();
        mailService.sendMail(from,to,subject,content, true);
    }
}

/*
        “/”：为特别单位，表示为“每”如“0/15”表示每隔15分钟执行一次,“0”表示为从“0”分开始,	“3/20”表示表示每隔20分钟执行一次，“3”表示从第3分钟开始执行

* */
