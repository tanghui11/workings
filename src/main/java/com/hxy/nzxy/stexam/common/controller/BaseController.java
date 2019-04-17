package com.hxy.nzxy.stexam.common.controller;

import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.system.domain.UserDO;
import org.thymeleaf.util.NumberUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

@Controller
public class BaseController {
    @Value("${auto-generate-id.prefix}")
    private String idPrefix;
    @Value("${application.name}")
    private String appName;

    public UserDO getUser() {
        return ShiroUtils.getUser();
    }

    //获取用户编号
    public String getUserId() {
        return getUser().getId();
    }

    //获取用户名称
    public String getUsername() {
        return getUser().getName();
    }

    //获取职员所在机构编号
    public String getOrgId() {
        return getUser().getOrgid();
    }

    //获取职员所在部门编号
    public String getDeptid() {
        return getUser().getDeptid();
    }

    //获取职员所在部门编号
    public String getWorkerid() {
        return getUser().getWorkerid();
    }

    public String getAppName() {
        return appName;
    }
    /**
     * 获得UUID
     *
     * @return String UUID
     */
    public String getUUID() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = format.format(date);
        //获取uuid
        String uuid = UUID.randomUUID().toString();
        return idPrefix + '_' + time + '_' + uuid.replaceAll("-", "");
    }

    /**
     * 传入试题难度系数，获取试题难度解释
     *
     * @param difficulty
     * @return
     */
    public String getExamQuestionDifficulty(Float difficulty) {
        if (difficulty == null) {
            return "";
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        if (!pattern.matcher(difficulty.toString()).matches()) {
            return "错误难度系数" + difficulty;
        }

        if (difficulty >= 0 && difficulty < 0.4) {
            return "难题";
        } else if (difficulty >= 0.4 && difficulty <= 0.7) {
            return "中等难度题";
        } else if (difficulty > 0.7 && difficulty <= 1) {
            return "容易题";
        } else {
            return "错误难度系数" + difficulty;
        }
    }
}