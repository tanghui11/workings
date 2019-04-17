package com.hxy.nzxy.stexam.center.school.controller;

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

import com.hxy.nzxy.stexam.domain.ScoreSchoolSetDO;
import com.hxy.nzxy.stexam.center.school.service.ScoreSchoolSetService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 校考课程录入设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:27
 */
 
@Controller
@RequestMapping("/school/scoreSchoolSet")
public class ScoreSchoolSetController extends SystemBaseController{
	@Autowired
	private ScoreSchoolSetService scoreSchoolSetService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("school:scoreSchoolSet:scoreSchoolSet")
	String ScoreSchoolSet(){
	    return "center/school/scoreSchoolSet/scoreSchoolSet";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("school:scoreSchoolSet:scoreSchoolSet")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreSchoolSetDO> scoreSchoolSetList = scoreSchoolSetService.list(query);
        for (ScoreSchoolSetDO item : scoreSchoolSetList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreSchoolSetService.count(query);
		PageUtils pageUtils = new PageUtils(scoreSchoolSetList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("school:scoreSchoolSet:add")
	String add(Model model){
	    return "center/school/scoreSchoolSet/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("school:scoreSchoolSet:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreSchoolSetDO scoreSchoolSet = scoreSchoolSetService.get(id);
		model.addAttribute("scoreSchoolSet", scoreSchoolSet);
	    return "center/school/scoreSchoolSet/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("school:scoreSchoolSet:add")
	public R save( ScoreSchoolSetDO scoreSchoolSet){
        scoreSchoolSet.setOperator(ShiroUtils.getUserId().toString());
		if(scoreSchoolSetService.save(scoreSchoolSet)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("school:scoreSchoolSet:edit")
	public R update( ScoreSchoolSetDO scoreSchoolSet){
	    scoreSchoolSet.setOperator(ShiroUtils.getUserId().toString());
		scoreSchoolSetService.update(scoreSchoolSet);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("school:scoreSchoolSet:remove")
	public R remove( Long id){
		if(scoreSchoolSetService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("school:scoreSchoolSet:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreSchoolSetService.batchRemove(ids);
		return R.ok();
	}
	
}
