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

import com.hxy.nzxy.stexam.domain.ScoreReplaceHisDO;
import com.hxy.nzxy.stexam.center.student.service.ScoreReplaceHisService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 免考成绩_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/scoreReplaceHis")
public class ScoreReplaceHisController extends SystemBaseController{
	@Autowired
	private ScoreReplaceHisService scoreReplaceHisService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreReplaceHis:scoreReplaceHis")
	String ScoreReplaceHis(){
	    return "center/student/scoreReplaceHis/scoreReplaceHis";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreReplaceHis:scoreReplaceHis")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreReplaceHisDO> scoreReplaceHisList = scoreReplaceHisService.list(query);
        for (ScoreReplaceHisDO item : scoreReplaceHisList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreReplaceHisService.count(query);
		PageUtils pageUtils = new PageUtils(scoreReplaceHisList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreReplaceHis:add")
	String add(Model model){
	    return "center/student/scoreReplaceHis/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreReplaceHis:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreReplaceHisDO scoreReplaceHis = scoreReplaceHisService.get(id);
		model.addAttribute("scoreReplaceHis", scoreReplaceHis);
	    return "center/student/scoreReplaceHis/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreReplaceHis:add")
	public R save( ScoreReplaceHisDO scoreReplaceHis){
        scoreReplaceHis.setOperator(ShiroUtils.getUserId().toString());
		if(scoreReplaceHisService.save(scoreReplaceHis)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreReplaceHis:edit")
	public R update( ScoreReplaceHisDO scoreReplaceHis){
	    scoreReplaceHis.setOperator(ShiroUtils.getUserId().toString());
		scoreReplaceHisService.update(scoreReplaceHis);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreReplaceHis:remove")
	public R remove( Long id){
		if(scoreReplaceHisService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreReplaceHis:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreReplaceHisService.batchRemove(ids);
		return R.ok();
	}
	
}
