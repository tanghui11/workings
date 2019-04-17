package com.hxy.nzxy.stexam.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.hxy.nzxy.stexam.system.domain.UserDO;

public class ShiroUtils {
    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }

    public static UserDO getUser() {
        return (UserDO) getSubjct().getSession().getAttribute("user");
    }

    public static String getUserId() {
        return getUser().getId();
    }

    public static void logout() {
        getSubjct().getSession().removeAttribute("user");
        getSubjct().logout();
    }
}
