package com.hxy.nzxy.stexam.oa.controller;

import com.hxy.nzxy.stexam.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

/**
 * 基类
 */
@Configuration
@Controller
public class OaBaseController extends BaseController {
    @Value("${application.name}")
    private String appName;

    public String getAppName() {
        return appName;
    }
}
