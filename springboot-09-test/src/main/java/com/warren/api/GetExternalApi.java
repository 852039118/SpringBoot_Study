package com.warren.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
* 网易云热评(JSONObject): http://localhost:3000/comment/hotwall/list
* 网易云热评(JSONObject): http://140.143.128.100:3000/comment/hotwall/list
* 今日诗词(String)：https://v1.jinrishici.com/rensheng.txt
* 词霸每日一句(JSONObject)：http://sentence.iciba.com/index.php?c=dailysentence&m=getdetail&title=2020-06-28
* */

@Component
public class GetExternalApi {

    //获取外部接口返回的整体数据 2020/6/28 By Warren
    public String getData(String url){
        //先调用下忽略https证书的再请求才可以
        HttpsUrlValidator.retrieveResponseFromServer(url);

        RestTemplate restTemplate=new RestTemplate();
        Map<String,String> params=new HashMap<>();
        params.put("name","dada");
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(url,String.class,params);
        // 获取响应体
        String body = responseEntity.getBody();
        System.out.println(body);
        return body;
    }
    public String getData(){
        return getData("https://v1.jinrishici.com/rensheng.txt");
    }

    // 每日 网易云热评 7.1日 待改
    public JSONObject getNetMusicContent(String url){
        String body = getData(url);    // "http://localhost:3000/comment/hotwall/list"

        // 转为JSON对象
        JSONObject jsonObject = JSON.parseObject(body);
        // 获取数据data 转为JSON对象数组
        JSONArray data = jsonObject.getJSONArray("data");
        // 获取 日
        Date date = new Date();
        int day = date.getDate();
        int month = date.getMonth();
        // 需要修改
        if (month == 5){
            day -= 28;
        }
        else if(month == 6){
            day += 1;
        }
        else{
            day -= 1;
        }
        // 获取指定对象
        JSONObject jsonObject1 = data.getJSONObject(day);
        // 获取评论内容
        String content = jsonObject1.getString("content");
        // 用户信息
        JSONObject simpleUserInfo = jsonObject1.getJSONObject("simpleUserInfo");
        String nickname = simpleUserInfo.getString("nickname");
        String avatar = simpleUserInfo.getString("avatar");
        // 歌曲信息
        JSONObject simpleResourceInfo = jsonObject1.getJSONObject("simpleResourceInfo");
        String name = simpleResourceInfo.getString("name");

        System.out.println(name);
        System.out.println(content);
        System.out.println(nickname);
        System.out.println(avatar);

        JSONObject returnJson = new JSONObject();

        returnJson.put("name",name);
        returnJson.put("content",content);
        returnJson.put("nickname",nickname);
        returnJson.put("avatar",avatar);

        return returnJson;
    }
    public JSONObject getNetMusicContent(){
        return getNetMusicContent("http://140.143.128.100:3000/comment/hotwall/list");
    }

    // 词霸 每日一句
    public JSONObject getPowerWord(String url){
        String body = getData(url);    // "http://localhost:3000/comment/hotwall/list"

        // 转为JSON对象
        JSONObject jsonObject = JSON.parseObject(body);

        // 获取英文内容
        String content = jsonObject.getString("content");
        // 获取中文内容
        String note = jsonObject.getString("note");

        System.out.println(content);
        System.out.println(note);

        JSONObject returnJson = new JSONObject();

        returnJson.put("content",content);
        returnJson.put("note",note);

        return returnJson;
    }
    // 词霸 每日一句(默认调用的接口)
    public JSONObject getPowerWord(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        return getPowerWord("http://sentence.iciba.com/index.php?c=dailysentence&m=getdetail&title=" + date);
    }


}
