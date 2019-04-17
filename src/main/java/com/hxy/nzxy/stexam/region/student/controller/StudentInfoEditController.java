package com.hxy.nzxy.stexam.region.student.controller;

import com.hxy.nzxy.stexam.common.utils.R;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.region.student.domain.StudentInfoEditDO;
import com.hxy.nzxy.stexam.region.student.service.StudentInfoEditService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author ypp
 * @Title: studentInfoEdit
 * @Description:
 * @date 2018/12/1210:44
 */
@Controller
@RequestMapping("/region/StudentInfoEdit")
public class StudentInfoEditController {

    @Autowired
    private StudentInfoEditService studentInfoEditService;

    @GetMapping()
    @RequiresPermissions("region:studentInfoEdit:studentInfoEdit")
    String CourseExemption(){
        return "region/student/studentInfoEdit/studentInfoEdit";
    }

    @ResponseBody
    @RequestMapping("/list/{studentid}")
    @RequiresPermissions("region:studentInfoEdit:studentInfoEdit")
    StudentInfoEditDO list(@PathVariable("studentid") Long studentid, Model model){
        StudentInfoEditDO studentInfoEdit = studentInfoEditService.list(studentid);

        model.addAttribute("studentInfoEdit",studentInfoEdit);
        return studentInfoEdit;
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("region:studentInfoEdit:add")
    public R save(StudentInfoEditDO studentInfoedit){
        studentInfoedit.setOperator(ShiroUtils.getUserId().toString());
        if(studentInfoEditService.save(studentInfoedit)>0){
            return R.ok();
        }
        return R.error();
    }
}
