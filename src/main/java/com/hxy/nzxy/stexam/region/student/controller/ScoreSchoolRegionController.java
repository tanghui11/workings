package com.hxy.nzxy.stexam.region.student.controller;

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

import com.hxy.nzxy.stexam.domain.ScoreSchoolDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreSchoolRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 校考成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
 
@Controller
@RequestMapping("/student/scoreSchoolRegion")
public class ScoreSchoolRegionController extends SystemBaseController{
	@Autowired
	private ScoreSchoolRegionService scoreSchoolRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreSchoolRegion:scoreSchoolRegion")
	String ScoreSchoolRegion(){
	    return "region/student/scoreSchoolRegion/scoreSchoolRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreSchoolRegion:scoreSchoolRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreSchoolDO> scoreSchoolRegionList = scoreSchoolRegionService.list(query);
        for (ScoreSchoolDO item : scoreSchoolRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreSchoolRegionService.count(query);
		PageUtils pageUtils = new PageUtils(scoreSchoolRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreSchoolRegion:add")
	String add(Model model){
	    return "region/student/scoreSchoolRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreSchoolRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreSchoolDO scoreSchoolRegion = scoreSchoolRegionService.get(id);
		model.addAttribute("scoreSchoolRegion", scoreSchoolRegion);
	    return "region/student/scoreSchoolRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreSchoolRegion:add")
	public R save( ScoreSchoolDO scoreSchoolRegion){
        scoreSchoolRegion.setOperator(ShiroUtils.getUserId().toString());
		if(scoreSchoolRegionService.save(scoreSchoolRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreSchoolRegion:edit")
	public R update( ScoreSchoolDO scoreSchoolRegion){
	    scoreSchoolRegion.setOperator(ShiroUtils.getUserId().toString());
		scoreSchoolRegionService.update(scoreSchoolRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreSchoolRegion:remove")
	public R remove( Long id){
		if(scoreSchoolRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreSchoolRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreSchoolRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
