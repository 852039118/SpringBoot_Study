package com.warren.config;

import com.warren.pojo.User;
import com.warren.service.UserService;
import com.warren.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

// 自定义的Realm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 拿到当前登陆的用户
        Subject subject = SecurityUtils.getSubject();

        User currentUser = (User) subject.getPrincipal();   // 拿到认证的User对象

        // 根据数据库权限 设置当前用户权限
        info.addStringPermission(currentUser.getPerms());
        System.out.println("执行了=>授权doGetAuthorizationInfo" + "权限为：" + currentUser.getPerms());
        return info;
    }

    // 认证,登陆的时候会执行
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        // 从数据库中查询
        User user = userService.queryUserByName(userToken.getUsername());
        System.out.println("执行了=>认证doGetAuthorizationInfo" + "用户名为:" + userToken.getUsername());
        if (user == null){
            return null;    // 抛出异常 UnknownAccountException
        }

        // 密码认证 shiro做
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPwd(), "");

        // 登录成功，存session
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("loginUser",user);

        return info;
    }
}
