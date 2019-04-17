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

import com.hxy.nzxy.stexam.domain.ScoreSchoolHisDO;
import com.hxy.nzxy.stexam.center.student.service.ScoreSchoolHisService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 校考成绩_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/scoreSchoolHis")
public class ScoreSchoolHisController extends SystemBaseController{
	@Autowired
	private ScoreSchoolHisService scoreSchoolHisService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreSchoolHis:scoreSchoolHis")
	String ScoreSchoolHis(){
	    return "center/student/scoreSchoolHis/scoreSchoolHis";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreSchoolHis:scoreSchoolHis")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreSchoolHisDO> scoreSchoolHisList = scoreSchoolHisService.list(query);
        for (ScoreSchoolHisDO item : scoreSchoolHisList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreSchoolHisService.count(query);
		PageUtils pageUtils = new PageUtils(scoreSchoolHisList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreSchoolHis:add")
	String add(Model model){
	    return "center/student/scoreSchoolHis/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreSchoolHis:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreSchoolHisDO scoreSchoolHis = scoreSchoolHisService.get(id);
		model.addAttribute("scoreSchoolHis", scoreSchoolHis);
	    return "center/student/scoreSchoolHis/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreSchoolHis:add")
	public R save( ScoreSchoolHisDO scoreSchoolHis){
        scoreSchoolHis.setOperator(ShiroUtils.getUserId().toString());
		if(scoreSchoolHisService.save(scoreSchoolHis)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreSchoolHis:edit")
	public R update( ScoreSchoolHisDO scoreSchoolHis){
	    scoreSchoolHis.setOperator(ShiroUtils.getUserId().toString());
		scoreSchoolHisService.update(scoreSchoolHis);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreSchoolHis:remove")
	public R remove( Long id){
		if(scoreSchoolHisService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreSchoolHis:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreSchoolHisService.batchRemove(ids);
		return R.ok();
	}
	
}
