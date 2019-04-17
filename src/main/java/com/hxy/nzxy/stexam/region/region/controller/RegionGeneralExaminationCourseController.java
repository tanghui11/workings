package com.hxy.nzxy.stexam.region.region.controller;

import com.hxy.nzxy.stexam.common.utils.PageUtils;
import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.RegionGeneralExaminationCourseDO;
import com.hxy.nzxy.stexam.region.region.service.RegionGeneralExaminationCourseService;
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
@RequestMapping("/region/regionGeneralExaminationCourse")
public class RegionGeneralExaminationCourseController extends SystemBaseController {

    @Autowired
    private RegionGeneralExaminationCourseService regionGeneralExaminationCourseService;

    @GetMapping()
    @RequiresPermissions("region:regionGeneralExaminationCourse:regionGeneralExaminationCourse")
    String CourseExemption(){
        return "region/region/regionCourseExemption/regionGeneralExaminationCourse";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("region:regionGeneralExaminationCourse:regionGeneralExaminationCourse")
    public PageUtils list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<RegionGeneralExaminationCourseDO> regionGeneralExaminationCourseList = regionGeneralExaminationCourseService.list(query);
        for (RegionGeneralExaminationCourseDO item : regionGeneralExaminationCourseList) {

        }
        int total = regionGeneralExaminationCourseService.count(query);
        PageUtils pageUtils = new PageUtils(regionGeneralExaminationCourseList, total);
        return pageUtils;
    }

}