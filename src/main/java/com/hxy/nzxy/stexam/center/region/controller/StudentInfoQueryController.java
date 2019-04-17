package com.hxy.nzxy.stexam.center.region.controller;

import com.hxy.nzxy.stexam.center.region.domain.StudentInfoQueryDO;
import com.hxy.nzxy.stexam.center.region.service.StudentInfoQueryService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.FieldDictUtil;
import com.hxy.nzxy.stexam.common.utils.PageUtils;
import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author ypp
 * @Title: StudentInfoQuery
 * @Description:
 * @date 2018/12/129:55
 */
@Controller
@RequestMapping("/region/studentInfoQuery")
public class StudentInfoQueryController extends SystemBaseController {
    @Autowired
    private StudentInfoQueryService studentInfoQueryService;
    @Autowired
    private CommonService commonService;
    @GetMapping()
    @RequiresPermissions("region:studentInfoQuery:studentInfoQuery")
    String StudentInfoQuery(Model model){
        model.addAttribute("stu_student_classify", commonService.listFieldDict(getAppName(), "stu_student", "classify"));
        return "center/region/studentInfoQuery/studentInfoQuery";
    }


    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("region:studentInfoQuery:studentInfoQuery")
    public PageUtils list(@RequestParam Map<String, Object> params){
        Long sum=0L;
        List<StudentInfoQueryDO> studentInfoQueryListCount = studentInfoQueryService.list(params);
        for (StudentInfoQueryDO studentInfo:studentInfoQueryListCount
             ) {
            sum+=studentInfo.getStudentCount();

        }


            Object limit = params.get("limit");
            if(limit==null){
                params.put("offset",0);
                params.put("limit",20);
        }
        //查询列表数据
        Query query = new Query(params);
        List<StudentInfoQueryDO> studentInfoQueryList = studentInfoQueryService.list(query);
        for (StudentInfoQueryDO item : studentInfoQueryList) {
            item.setName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "code", item.getSchoolid())+" "+
            FieldDictUtil.get(getAppName(),"sch_school_nzxy","name",item.getSchoolid())
            );
        }
        int total = studentInfoQueryService.count(query);
        PageUtils pageUtils = new PageUtils(studentInfoQueryList, total);
        pageUtils.setMessages(sum.toString());
        return pageUtils;
    }
    @GetMapping("/detail")
    @RequiresPermissions("region:studentInfoQuery:studentInfoQuery")
    String detail(Model model){
        return "center/region/studentInfoQuery/detail";
    }
    //查看明细
    @ResponseBody
    @GetMapping("/listDetail")
    @RequiresPermissions("region:studentInfoQuery:studentInfoQuery")
    public PageUtils listDetail(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<StudentInfoQueryDO> listDetail = studentInfoQueryService.listDetail(query);
     /*   for (StudentInfoQueryDO item : listDetail) {
            item.setName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "code", item.getSchoolid())+" "+
                    FieldDictUtil.get(getAppName(),"sch_school_nzxy","name",item.getSchoolid())
            );
        }*/
        int total = studentInfoQueryService.countDetail(query);
        PageUtils pageUtils = new PageUtils(listDetail, total);
        return pageUtils;
    }
}