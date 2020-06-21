package com.warren.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class ScheduledService {

    @Autowired
    MailService mailService;

    // Cron表达式
    // 秒 分 时 日 月 年
    // 在一个特定的和时间执行这个方法~ Timer
    @Scheduled(cron = "0 0 9 * * ?")
    public void morning() throws MessagingException {
        System.out.println("早上好，汪老板，今天即将是美好的一天！ ");
//        mailService.sendMail();
    }

    @Scheduled(cron = "0 3 23 * * ?")
    public void evening() throws MessagingException {
        System.out.println("汪老板，您该就寝了");
//        mailService.sendMail();
    }
}

/*
        “/”：为特别单位，表示为“每”如“0/15”表示每隔15分钟执行一次,“0”表示为从“0”分开始,	“3/20”表示表示每隔20分钟执行一次，“3”表示从第3分钟开始执行

* */
