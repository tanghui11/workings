package com.hxy.nzxy.stexam.school.school.controller;

import com.hxy.nzxy.stexam.common.utils.FieldDictUtil;
import com.hxy.nzxy.stexam.common.utils.PageUtils;
import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.common.utils.UserUtil;
import com.hxy.nzxy.stexam.domain.CourseExemptionDO;
import com.hxy.nzxy.stexam.school.school.service.CourseExemptionService;
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
@RequestMapping("/school/courseExemption")
public class CourseExemptionController extends SystemBaseController {

    @Autowired
    private CourseExemptionService courseExemptionService;

    @GetMapping()
    @RequiresPermissions("school:courseExemption:courseExemption")
    String CourseExemption(){
        return "school/school/courseExemption/courseExemption";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("school:courseExemption:courseExemption")
    public PageUtils list(@RequestParam Map<String, Object> params){

        //查询列表数据
        Query query = new Query(params);
        List<CourseExemptionDO> courseExemptionList = courseExemptionService.list(query);
        for (CourseExemptionDO item : courseExemptionList) {
            item.setAuditStatus(FieldDictUtil.get(getAppName(),"stu_student_speciality","audit_status",item.getAuditStatus()));
            item.setStatus(FieldDictUtil.get(getAppName(),"stu_student_speciality","status",item.getStatus()));
            item.setEducation(FieldDictUtil.get(getAppName(),"stu_student_speciality","education",item.getEducation()));
            item.setOperator(UserUtil.getName(item.getOperator()));
        }

        int total = courseExemptionService.count(query);
        PageUtils pageUtils = new PageUtils(courseExemptionList, total);
        return pageUtils;
    }

}