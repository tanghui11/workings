package com.hxy.nzxy.stexam.center.student.controller;

import com.hxy.nzxy.stexam.center.student.service.WarehousingScoreEditService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.WarehousingScoreEditRecordDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/student/WarehousingScoreEditRecord")
public class WarehousingScoreEditRecordController extends SystemBaseController{
	@Autowired
	private WarehousingScoreEditService warehousingScoreEditService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:WarehousingScoreEdit:WarehousingScoreEditRecord")
	String PracticeSchool(){
	    return "center/student/WarehousingScoreEdit/WarehousingScoreEditRecord";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:WarehousingScoreEdit:WarehousingScoreEditRecord")
	public PageUtils warehousingScoreEditRecordList(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<WarehousingScoreEditRecordDO> warehousingScoreEditRecordList = warehousingScoreEditService.warehousingScoreEditRecordList(query);
        for (WarehousingScoreEditRecordDO item : warehousingScoreEditRecordList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = warehousingScoreEditService.warehousingScoreEditRecordCount(query);
		PageUtils pageUtils = new PageUtils(warehousingScoreEditRecordList, total);
		return pageUtils;
	}

	/**
	 * 删除
	 */
	@PostMapping( "/recordRemove")
	@ResponseBody
	@RequiresPermissions("student:studentScoreIn:remove")
	public R remove( Long id){
		if(warehousingScoreEditService.recordRemove(Long.valueOf(id))>0){
			return R.ok();
		}
		return R.error();
	}
}