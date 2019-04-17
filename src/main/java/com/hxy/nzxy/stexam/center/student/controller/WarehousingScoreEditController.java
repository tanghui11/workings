package com.hxy.nzxy.stexam.center.student.controller;

import com.hxy.nzxy.stexam.center.student.service.WarehousingScoreEditService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.WarehousingScoreEditDO;
import com.hxy.nzxy.stexam.domain.WarehousingScoreEditRecordDO;
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
 *功能描述 已入库成绩修改
 * @author ypp
 * @date 2018/11/27
 * @param
 * @return
 */
 
@Controller
@RequestMapping("/student/WarehousingScoreEdit")
public class WarehousingScoreEditController extends SystemBaseController{
	@Autowired
	private WarehousingScoreEditService warehousingScoreEditService;

	@GetMapping()
	@RequiresPermissions("student:WarehousingScoreEdit:WarehousingScoreEdit")
	String PracticeSchool(){
	    return "center/student/WarehousingScoreEdit/WarehousingScoreEdit";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:WarehousingScoreEdit:WarehousingScoreEdit")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<WarehousingScoreEditDO> warehousingScoreEditList = warehousingScoreEditService.list(query);
        for (WarehousingScoreEditDO item : warehousingScoreEditList) {
        	item.setStatus(FieldDictUtil.get(getAppName(),"stu_score","status",item.getStatus()));
			item.setExamFlag(FieldDictUtil.get(getAppName(),"stu_score","exam_flag",item.getExamFlag()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));

        }
        WarehousingScoreEditDO warehousingScoreEditDO;
		int total = warehousingScoreEditService.count(query);
		PageUtils pageUtils = new PageUtils(warehousingScoreEditList, total);
		return pageUtils;
	}

	/**
	 * 修改成绩
	 */
	@ResponseBody
//	@Log("修改成绩")
	@RequestMapping("/update")
	@RequiresPermissions("student:WarehousingScoreEdit:edit")
	public R update(WarehousingScoreEditRecordDO warehousingScoreEditRecord, @RequestParam("grade") String grade,@RequestParam("newGrade") String newGrade, @RequestParam("remark") String remark){
	    warehousingScoreEditRecord.setOperator(ShiroUtils.getUserId().toString());
		WarehousingScoreEditDO warehousingScoreEditDO = new WarehousingScoreEditDO();
		warehousingScoreEditRecord.setGrade(grade);
		System.out.println(warehousingScoreEditRecord.getScoreid());
		warehousingScoreEditDO.setNewGrade(newGrade);
        warehousingScoreEditService.saveRecord(warehousingScoreEditRecord);
		warehousingScoreEditService.update(warehousingScoreEditRecord);
		return R.ok();
	}

}