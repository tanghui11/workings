package com.hxy.nzxy.stexam.system.vo;

import com.hxy.nzxy.stexam.system.domain.UserDO;

/**
 * @author gaoyuzhe
 * @date 2017/12/15.
 */
public class UserVO {
    /**
     * 旧密码
     */
    private String pwdOld;
    /**
     * 新密码
     */
    private String pwdNew;

    public String getPwdOld() {
        return pwdOld;
    }

    public void setPwdOld(String pwdOld) {
        this.pwdOld = pwdOld;
    }

    public String getPwdNew() {
        return pwdNew;
    }

    public void setPwdNew(String pwdNew) {
        this.pwdNew = pwdNew;
    }
}
