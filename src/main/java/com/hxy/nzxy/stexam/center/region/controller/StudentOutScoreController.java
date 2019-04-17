package com.hxy.nzxy.stexam.center.region.controller;

import com.hxy.nzxy.stexam.center.region.service.StudentOutScoreService;
import com.hxy.nzxy.stexam.common.utils.FieldDictUtil;
import com.hxy.nzxy.stexam.common.utils.PageUtils;
import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.StudentOutScoreDO;
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
 * 考绩转出
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:19
 */
 
@Controller
@RequestMapping("/region/studentOutScore")
public class StudentOutScoreController extends SystemBaseController{
	@Autowired
	private StudentOutScoreService studentOutScoreService;

    @GetMapping()
	@RequiresPermissions("region:studentOut:studentOutScore")
	String StudentOut(){
	    return "center/region/studentOut/studentOutScore";
	}

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("region:studentOut:studentOutScore")
    public PageUtils list(@RequestParam Map<String, Object> params){
        //查询列表数据

        Query query = new Query(params);
        List<StudentOutScoreDO> studentOutScoreList = studentOutScoreService.list(query);
        for (StudentOutScoreDO item : studentOutScoreList) {
            item.setType(FieldDictUtil.get(getAppName(),"stu_score","type",item.getType()));

        }
        int total = studentOutScoreService.count(query);
        PageUtils pageUtils = new PageUtils(studentOutScoreList, total);
        return pageUtils;
    }
}