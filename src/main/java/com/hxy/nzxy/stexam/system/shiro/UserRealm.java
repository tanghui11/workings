package com.hxy.nzxy.stexam.system.shiro;


import java.util.Set;
import com.hxy.nzxy.stexam.common.config.ApplicationContextRegister;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.common.utils.SpringContextUtil;
import com.hxy.nzxy.stexam.system.dao.UserRealmDao;
import com.hxy.nzxy.stexam.system.dao.WorkerDao;
import com.hxy.nzxy.stexam.system.domain.WorkerDO;
import com.hxy.nzxy.stexam.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import com.hxy.nzxy.stexam.system.domain.UserDO;
import com.hxy.nzxy.stexam.system.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserRealmDao userDao;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        String userId = ShiroUtils.getUserId();
        MenuService menuService = ApplicationContextRegister.getBean(MenuService.class);
        Set<String> perms = menuService.listPerms(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(perms);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        //查询用户类型
        UserDO userType = userDao.getUserType(username);
        // 查询用户信息
        UserDO user =new UserDO();
        if(userType.getType().equals("1")){
            user=  userDao.getByName(username);
        }else if(userType.getType().equals("2")){
            user=  userDao.getRegionByName(username);
        }else if(userType.getType().equals("3")){
            user=  userDao.getSchoolByName(username);
        }else if(userType.getType().equals("4")){
            user=  userDao.getCollegeByName(username);
        }

        // 账号不存在
        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        // 密码错误
        if (!password.equals(user.getPwd())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        // 账号锁定
        if (user.getStatus() == "b") {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        SecurityUtils.getSubject().getSession().setAttribute("user", user);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }
        private UserRealm getService(){
          return  SpringContextUtil.getBean(this.getClass());
        }
}
