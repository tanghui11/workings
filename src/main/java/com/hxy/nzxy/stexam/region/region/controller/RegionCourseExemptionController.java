package com.hxy.nzxy.stexam.region.region.controller;

import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.RegionCourseExemptionDO;
import com.hxy.nzxy.stexam.domain.StudentDO;
import com.hxy.nzxy.stexam.region.region.service.RegionCourseExemptionService;
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
@RequestMapping("/region/regionCourseExemption")
public class RegionCourseExemptionController extends SystemBaseController {

    @Autowired
    private RegionCourseExemptionService regionCourseExemptionService;

    @GetMapping()
    @RequiresPermissions("region:regionCourseExemption:regionCourseExemption")
    String CourseExemption(){
        return "region/region/regionCourseExemption/regionCourseExemption";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("region:regionCourseExemption:regionCourseExemption")
    public PageUtils list(@RequestParam Map<String, Object> params){
        params.put("classify","c");
        Query query = new Query(params);
        List<StudentDO> regionCourseExemptionList = regionCourseExemptionService.list(query);

        for (StudentDO item : regionCourseExemptionList) {
            item.setGroupName(FieldDictUtil.get(getAppName(), "reg_groups", "id", item.getGroupid() + ""));
            item.setType(FieldDictUtil.get(getAppName(), "pla_speciality_record", "type", item.getType()));
            item.setGender(FieldDictUtil.get(getAppName(), "stu_student", "gender", item.getGender()));
            item.setHomeType(FieldDictUtil.get(getAppName(), "stu_student", "home_type", item.getHomeType()));
            item.setPolitics(FieldDictUtil.get(getAppName(), "stu_student", "politics", item.getPolitics()));
            item.setNation(FieldDictUtil.get(getAppName(), "stu_student", "nation", item.getNation()));
            item.setProfession(FieldDictUtil.get(getAppName(), "stu_student", "profession", item.getProfession()));
            item.setEducation(FieldDictUtil.get(getAppName(), "stu_student_speciality", "education", item.getEducation()));
            item.setCertificateType(FieldDictUtil.get(getAppName(), "stu_student", "certificate_type", item.getCertificateType()));
            item.setClassify(FieldDictUtil.get(getAppName(), "stu_student", "classify", item.getClassify()));
            item.setGradSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getGradSpecialityid()));
            item.setSpecialityName(item.getSpecialityid()+FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
            item.setAuditStatus(FieldDictUtil.get(getAppName(),"stu_student_speciality","audit_status",item.getAuditStatus()));
            item.setStatus(FieldDictUtil.get(getAppName(),"stu_student_speciality","status",item.getStatus()));

        }

        //查询列表数据
   /*     Query query = new Query(params);
        List<RegionCourseExemptionDO> regionCourseExemptionList = regionCourseExemptionService.list(query);
        for (RegionCourseExemptionDO item : regionCourseExemptionList) {
            item.setAuditStatus(FieldDictUtil.get(getAppName(),"stu_student_speciality","audit_status",item.getAuditStatus()));
            item.setStatus(FieldDictUtil.get(getAppName(),"stu_student_speciality","status",item.getStatus()));
            item.setEducation(FieldDictUtil.get(getAppName(),"stu_student_speciality","education",item.getEducation()));
            item.setOperator(UserUtil.getName(item.getOperator()));
        }*/

        int total = regionCourseExemptionService.count(query);
        PageUtils pageUtils = new PageUtils(regionCourseExemptionList, total);
        return pageUtils;
    }

}