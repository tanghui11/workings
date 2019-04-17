package com.hxy.nzxy.stexam.center.region.controller;

import com.hxy.nzxy.stexam.center.region.domain.StudentRegionQueryDO;
import com.hxy.nzxy.stexam.center.region.service.StudentRegionQueryService;
import com.hxy.nzxy.stexam.common.utils.*;
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

/**考生报考地区查询
 * @author ypp
 * @Title: StudentRegionQuery
 * @Description:
 * @date 2018/12/1212:00
 */
@Controller
@RequestMapping("/region/studentRegionQuery")
public class StudentRegionQueryController extends SystemBaseController {

        @Autowired
        private StudentRegionQueryService studentRegionQueryService;

        @GetMapping()
        @RequiresPermissions("region:studentRegionQuery:studentRegionQuery")
        String StudentRegionQuery(){
            return "center/region/studentRegionQuery/studentRegionQuery";
        }

        @ResponseBody
        @GetMapping("/list")
        @RequiresPermissions("region:studentRegionQuery:studentRegionQuery")
        public PageUtils list(@RequestParam Map<String, Object> params){
            //查询列表数据
            Query query = new Query(params);
             List<StudentRegionQueryDO> studentRegionQueryList = studentRegionQueryService.list(query);
            for (StudentRegionQueryDO item : studentRegionQueryList) {
                item.setType(FieldDictUtil.get(getAppName(), "stu_student_course", "type", item.getType()));
                item.setOperator(UserUtil.getName(item.getOperator()));
            }
            int total = studentRegionQueryService.count(query);
            PageUtils pageUtils = new PageUtils(studentRegionQueryList, total);
            return pageUtils;
        }

    }