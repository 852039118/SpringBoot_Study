package com.warren.config;


import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    // ShiroFilterFactoryBean:3
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        // 添加Shiro的内置过滤器
        /*
        *   anon: 无需认证
        *   authc: 必须认证
        *   user: 必须拥有 记住我 功能
        *   perms: 拥有对某个资源的权限才可以访问
        *   role: 拥有某个角色权限才可以访问
        * */

        Map<String, String> filterMap = new LinkedHashMap<String,String>();
        filterMap.put("/user/*","authc");
        bean.setFilterChainDefinitionMap(filterMap);

        // 设置登录的请求
        bean.setLoginUrl("/toLogin");

        return bean;
    }

    // DefaultWebSecurityManager:2
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    // 创建 realm 对象， 需要自定义类:1
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
