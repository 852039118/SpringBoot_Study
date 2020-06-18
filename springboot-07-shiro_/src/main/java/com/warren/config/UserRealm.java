package com.warren.config;

import com.warren.pojo.User;
import com.warren.service.UserService;
import com.warren.service.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

// 自定义的Realm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");
        return null;
    }

    // 认证,登陆的时候会执行
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthorizationInfo");

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        // 从数据库中查询
        User user = userService.queryUserByName(userToken.getUsername());

        if (user == null){
            return null;    // 抛出异常 UnknownAccountException
        }

        // 密码认证 shiro做
        return new SimpleAuthenticationInfo("",user.getPwd(),"");
    }
}
