package com.hxy.nzxy.stexam.system.shiro;

import javax.annotation.PostConstruct;

import com.hxy.nzxy.stexam.common.config.ApplicationContextRegister;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.system.config.ShiroCasConfig;
import com.hxy.nzxy.stexam.system.dao.UserDao;
import com.hxy.nzxy.stexam.system.domain.UserDO;
import com.hxy.nzxy.stexam.system.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * Created by hdwang on 2017/6/20.
 * 安全数据源
 */
public class ShiroCasRealm extends CasRealm {

//    private static final Logger logger = LoggerFactory.getLogger(ShiroCasRealm.class);
//
//    @Autowired
//    private UserDao userDao;
//
//    public void ShiroCasRealm() {
//        setAuthenticationTokenClass(AuthenticationToken.class);
//    }
//
//    @PostConstruct
//    public void initProperty() {
//        setCasServerUrlPrefix(ShiroCasConfig.casServerUrlPrefix);
//        // 客户端回调地址
//        setCasService(ShiroCasConfig.shiroServerUrlPrefix + ShiroCasConfig.casFilterUrlPattern);
//    }
//
//    /**
//     * 1、CAS认证 ,验证用户身份
//     * 2、将用户基本信息设置到会话中(不用了，随时可以获取的)
//     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
//        try {
//            AuthenticationInfo authc = super.doGetAuthenticationInfo(token);
//            if (authc == null) return null;
//            String account = (String) authc.getPrincipals().getPrimaryPrincipal();
//            UserDO user = userDao.getByName(account);
//            //将用户信息存入session中
//            SecurityUtils.getSubject().getSession().setAttribute("user", user);
//            return authc;
//        } catch (AuthenticationException e) {
//            throw new UnknownAccountException("账号或密码不正确");
//        }
//    }
//
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        Long userId = ShiroUtils.getUserId();
//        MenuService menuService = ApplicationContextRegister.getBean(MenuService.class);
//        Set<String> perms = menuService.listPerms(userId);
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.setStringPermissions(perms);
//        return info;
//    }
}