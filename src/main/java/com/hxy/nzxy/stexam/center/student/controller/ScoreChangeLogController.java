package com.hxy.nzxy.stexam.center.student.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxy.nzxy.stexam.domain.ScoreChangeLogDO;
import com.hxy.nzxy.stexam.center.student.service.ScoreChangeLogService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 成绩变更日志表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
 
@Controller
@RequestMapping("/student/scoreChangeLog")
public class ScoreChangeLogController extends SystemBaseController{
	@Autowired
	private ScoreChangeLogService scoreChangeLogService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreChangeLog:scoreChangeLog")
	String ScoreChangeLog(){
	    return "center/student/scoreChangeLog/scoreChangeLog";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreChangeLog:scoreChangeLog")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreChangeLogDO> scoreChangeLogList = scoreChangeLogService.list(query);
        for (ScoreChangeLogDO item : scoreChangeLogList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreChangeLogService.count(query);
		PageUtils pageUtils = new PageUtils(scoreChangeLogList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreChangeLog:add")
	String add(Model model){
	    return "center/student/scoreChangeLog/add";
	}

	@GetMapping("/edit/{changeType}")
	@RequiresPermissions("student:scoreChangeLog:edit")
	String edit(@PathVariable("changeType") String changeType,Model model){
		ScoreChangeLogDO scoreChangeLog = scoreChangeLogService.get(changeType);
		model.addAttribute("scoreChangeLog", scoreChangeLog);
	    return "center/student/scoreChangeLog/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreChangeLog:add")
	public R save( ScoreChangeLogDO scoreChangeLog){
        scoreChangeLog.setOperator(ShiroUtils.getUserId().toString());
		if(scoreChangeLogService.save(scoreChangeLog)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreChangeLog:edit")
	public R update( ScoreChangeLogDO scoreChangeLog){
	    scoreChangeLog.setOperator(ShiroUtils.getUserId().toString());
		scoreChangeLogService.update(scoreChangeLog);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreChangeLog:remove")
	public R remove( String changeType){
		if(scoreChangeLogService.remove(changeType)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreChangeLog:batchRemove")
	public R remove(@RequestParam("ids[]") String[] changeTypes){
		scoreChangeLogService.batchRemove(changeTypes);
		return R.ok();
	}
	
}
