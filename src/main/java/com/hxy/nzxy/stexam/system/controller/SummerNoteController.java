package com.hxy.nzxy.stexam.system.controller;

import com.hxy.nzxy.stexam.common.annotation.Log;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.system.domain.RoleDO;
import com.hxy.nzxy.stexam.system.domain.UserDO;
import com.hxy.nzxy.stexam.system.service.*;
import com.hxy.nzxy.stexam.system.vo.UserVO;
import com.hxy.nzxy.stexam.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@RequestMapping("/system/summernote")
@Controller
public class SummerNoteController extends SystemBaseController {
    @Value("${stexam.uploadPath.summernote}")
    private String summernotePath;
    @Value("${stexam.uploadPath.summernoteUrl}")
    private String summernoteUrl;
    @Value("${stexam.uploadPath.summernoteFileExtension}")
    private String summernoteFileExtension;

    /**
     * 上传附件
     */
    @ResponseBody
    @PostMapping("/upload")
    public R upload(@RequestParam("subPath") String subPath, @RequestParam("file") MultipartFile file) {
        //检测文件
        if (!this.checkFile(file.getOriginalFilename())) {
            return R.error("此文件类型不允许上传！");
        }
        //创建文件目录，格式为 /分组/年/月/文件.后缀
        Calendar cal = Calendar.getInstance();
        String year = String.valueOf(cal.get(Calendar.YEAR));
        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        String path = summernotePath;
        if (subPath.equals("help")) {
            path += subPath + "/";
        } else {
            path += subPath + "/" + year + "/" + month + "/";
        }
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //文件名称
        path += getUUID() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //保存文件
        File targetFile = new File(path);
        try {
            file.transferTo(targetFile);
            R ret = new R();
            ret.put("name", file.getOriginalFilename());
            ret.put("url", summernoteUrl + path.replace(summernotePath, ""));
            return ret;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.error("上传失败！");
    }

    private boolean checkFile(String fileName) {
        boolean flag = false;
        //获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if (summernoteFileExtension.contains(suffix.trim().toLowerCase())) {
            flag = true;
        }
        return flag;
    }
}
