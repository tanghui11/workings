package com.hxy.nzxy.stexam.system.controller;

import com.hxy.nzxy.stexam.common.controller.BaseController;
import com.hxy.nzxy.stexam.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

/**
 * 基类
 */
@Configuration
@Controller
public class SystemBaseController extends BaseController {
    @Value("${application.name}")
    private String appName;

    public String getAppName() {
        return appName;
    }
}
