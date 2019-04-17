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

import com.hxy.nzxy.stexam.domain.ScoreInHisDO;
import com.hxy.nzxy.stexam.center.student.service.ScoreInHisService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 转入成绩_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
 
@Controller
@RequestMapping("/student/scoreInHis")
public class ScoreInHisController extends SystemBaseController{
	@Autowired
	private ScoreInHisService scoreInHisService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreInHis:scoreInHis")
	String ScoreInHis(){
	    return "center/student/scoreInHis/scoreInHis";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreInHis:scoreInHis")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreInHisDO> scoreInHisList = scoreInHisService.list(query);
        for (ScoreInHisDO item : scoreInHisList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreInHisService.count(query);
		PageUtils pageUtils = new PageUtils(scoreInHisList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreInHis:add")
	String add(Model model){
	    return "center/student/scoreInHis/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreInHis:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreInHisDO scoreInHis = scoreInHisService.get(id);
		model.addAttribute("scoreInHis", scoreInHis);
	    return "center/student/scoreInHis/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreInHis:add")
	public R save( ScoreInHisDO scoreInHis){
        scoreInHis.setOperator(ShiroUtils.getUserId().toString());
		if(scoreInHisService.save(scoreInHis)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreInHis:edit")
	public R update( ScoreInHisDO scoreInHis){
	    scoreInHis.setOperator(ShiroUtils.getUserId().toString());
		scoreInHisService.update(scoreInHis);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreInHis:remove")
	public R remove( Long id){
		if(scoreInHisService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreInHis:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreInHisService.batchRemove(ids);
		return R.ok();
	}
	
}
