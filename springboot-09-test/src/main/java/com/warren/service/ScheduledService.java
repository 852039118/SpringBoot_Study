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
    void initEveryday() throws Exception {
        subject = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " Happy~";
        // 如果接口访问失败，就用我写的诗吧 :)
/*      2020-07-28
        String verse = "盛年不重来，一日难再晨。花有重开日，人无再少年！" ;
        String musicContent = "我不是失眠了，只是被什么勾起回忆了。我不是记性越来越差了，可能它存着重要的东西腾不出位置。";
        String nickname = "Warren";
        String name = "孤独的总和";
        String wordContent = "Follow Excellence. Success will chase you.";
        String note = "追求卓越,成功就会在不经意间追上你。";*/
        String verse = "心若自由，身沐长风。一念天堂，一念地狱。" ;
        String musicContent = "人活一世, 值得爱的东西很多  不要因为某一个不满意就灰心  即使生活有一千个理由让你哭  你也要找到一个理由让自己笑  我是个悲观的人, 很容易被生活击垮  也是个乐观的人, 一点小小的温暖就能重新燃起对生活的希望";
        // 夏天太浪漫了 我想请它吃顿饭 如果你有空 也可以一起来
        String nickname = "君若一盏清茗";
        String name = "愿你温柔且有力量";
        String wordContent = "Being surrounded by the wrong people is the loneliest thing in the world.";
        String note = "被错误的人围绕着才是世上最孤独的事。";

        try {
            // 调用接口获取今日返回的JSON对象数据
            JSONObject powerWord = everyData.getPowerWord();
            JSONObject music = everyData.getNetMusicContent();
            // 网易云音乐评论数据
            name = music.getString("name");
            musicContent = music.getString("content");
            nickname = music.getString("nickname");
//            String avatar = music.getString("avatar");
            // 词霸 每日一句
            wordContent = powerWord.getString("content");
            note = powerWord.getString("note");
            // 每日一词
            verse = everyData.getData();
        }catch (Exception e){
            e.printStackTrace();
            //抛出异常后，就不会继续执行后面了
//            throw new Exception(e.getMessage());
        }

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
                "<br>\n" +
                note +
                "            </i></b>\n" +
                "        </p>\n" +
                "    </div>\n" +
                "</div>";
//        System.out.println(content);
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
    public void evening() throws Exception {
        this.initEveryday();
        mailService.sendMail(from,"1377818125@qq.com",subject,content, true);
        mailService.sendMail(from,to,subject,content, true);
    }

/*    @Scheduled(cron = "0/59 * * * * ?")
    public void scheduledTest() throws Exception {
//        System.out.println("每隔30秒发送一次");
        this.initEveryday();
        mailService.sendMail(from,to,subject,content, true);
    }*/

  // 隔半小时一次
//    @Scheduled(cron = "0 0/30 * 28 6 ?")
//    public void scheduledTest1() throws MessagingException {
//        this.initEveryday();
//        mailService.sendMail(from,to,subject,content, true);
//    }

    @Scheduled(cron = "0 0 21 11 8 ?")
    public void uploadTest() throws Exception {
        this.initEveryday();
        mailService.sendMail(from,to,subject,content, true);
    }
}

/*
        “/”：为特别单位，表示为“每”如“0/15”表示每隔15分钟执行一次,“0”表示为从“0”分开始,	“3/20”表示表示每隔20分钟执行一次，“3”表示从第3分钟开始执行

* */
