package com.hxy.nzxy.stexam.common.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * 学生承诺书确认
 **/
@Controller
@RequestMapping("/face/clsrz")
public class FaceCheckController extends BaseController {
    String prefix = "common/face";
    @GetMapping()
    String inits() {
        return prefix + "/examCls";
    }
    @RequestMapping("/video")
    String voide() {
        return prefix + "/check_video";
    }
}
