package com.warren.controller;

import com.warren.model.NetMusic;
import com.warren.service.AsyncService;
import com.warren.service.ScheduledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @Autowired
    AsyncService asyncService;
    @Autowired
    NetMusic netMusic;
    @Autowired
    ScheduledService scheduledService;

    @RequestMapping("hello")
    public String hello() throws Exception {

        asyncService.hello();

        return "异步执行3秒 Success 2020/8/11版本";
    }

    // 可以访问此接口来改变发送每日热评的索引，由0~29
    @GetMapping("setMusicIndex/{index}")
    public int setMusicIndex(@PathVariable int index) throws Exception {
        if (index >= 0 && index <= 29){
            netMusic.setIndex(index);
            System.out.println("修改了热评索引为：" + index);
            // 测试一封邮件
            scheduledService.uploadTest();
        }
        return netMusic.getIndex();
    }

    // 获取发送每日热评的索引
    @GetMapping("getMusicIndex")
    public int getMusicIndex() throws Exception {
        // 测试一封邮件
        scheduledService.uploadTest();
        return netMusic.getIndex();
    }
}
