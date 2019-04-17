package com.hxy.nzxy.stexam.school.school.controller;

import com.hxy.nzxy.stexam.common.utils.PageUtils;
import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.GeneralExaminationCourseDO;
import com.hxy.nzxy.stexam.school.school.service.GeneralExaminationCourseService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 课程免考申请
 * @author ypp
 * @date 2018/11/20
 * @param
 * @return
 */
@Controller
@RequestMapping("/school/GeneralExaminationCourse")
public class GeneralExaminationCourseController extends SystemBaseController {

    @Autowired
    private GeneralExaminationCourseService generalExaminationCourseService;

    @GetMapping()
    @RequiresPermissions("school:GeneralExaminationCourse:GeneralExaminationCourse")
    String CourseExemption(){
        return "school/school/courseExemption/generalExaminationCourse";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("school:GeneralExaminationCourse:GeneralExaminationCourse")
    public PageUtils list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<GeneralExaminationCourseDO> generalExaminationCourseList = generalExaminationCourseService.list(query);
        for (GeneralExaminationCourseDO item : generalExaminationCourseList) {
        }
        int total = generalExaminationCourseService.count(query);
        PageUtils pageUtils = new PageUtils(generalExaminationCourseList, total);
        return pageUtils;
    }

}