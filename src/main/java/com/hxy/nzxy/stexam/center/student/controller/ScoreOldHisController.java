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

import com.hxy.nzxy.stexam.domain.ScoreOldHisDO;
import com.hxy.nzxy.stexam.center.student.service.ScoreOldHisService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 老课程成绩_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
 
@Controller
@RequestMapping("/student/scoreOldHis")
public class ScoreOldHisController extends SystemBaseController{
	@Autowired
	private ScoreOldHisService scoreOldHisService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreOldHis:scoreOldHis")
	String ScoreOldHis(){
	    return "center/student/scoreOldHis/scoreOldHis";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreOldHis:scoreOldHis")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreOldHisDO> scoreOldHisList = scoreOldHisService.list(query);
        for (ScoreOldHisDO item : scoreOldHisList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreOldHisService.count(query);
		PageUtils pageUtils = new PageUtils(scoreOldHisList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreOldHis:add")
	String add(Model model){
	    return "center/student/scoreOldHis/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreOldHis:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreOldHisDO scoreOldHis = scoreOldHisService.get(id);
		model.addAttribute("scoreOldHis", scoreOldHis);
	    return "center/student/scoreOldHis/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreOldHis:add")
	public R save( ScoreOldHisDO scoreOldHis){
        scoreOldHis.setOperator(ShiroUtils.getUserId().toString());
		if(scoreOldHisService.save(scoreOldHis)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreOldHis:edit")
	public R update( ScoreOldHisDO scoreOldHis){
	    scoreOldHis.setOperator(ShiroUtils.getUserId().toString());
		scoreOldHisService.update(scoreOldHis);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreOldHis:remove")
	public R remove( Long id){
		if(scoreOldHisService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreOldHis:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreOldHisService.batchRemove(ids);
		return R.ok();
	}
	
}
