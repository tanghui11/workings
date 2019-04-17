package com.hxy.nzxy.stexam.school.school.controller;

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
import com.hxy.nzxy.stexam.school.school.service.ScoreSchoolSetSchoolService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 校考课程录入设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 20:36:41
 */
 
@Controller
@RequestMapping("/school/scoreSchoolSetSchool")
public class ScoreSchoolSetSchoolController extends SystemBaseController{
	@Autowired
	private ScoreSchoolSetSchoolService scoreSchoolSetSchoolService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("school:scoreSchoolSetSchool:scoreSchoolSetSchool")
	String ScoreSchoolSetSchool(){
	    return "school/school/scoreSchoolSetSchool/scoreSchoolSetSchool";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("school:scoreSchoolSetSchool:scoreSchoolSetSchool")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreSchoolSetDO> scoreSchoolSetSchoolList = scoreSchoolSetSchoolService.list(query);
        for (ScoreSchoolSetDO item : scoreSchoolSetSchoolList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreSchoolSetSchoolService.count(query);
		PageUtils pageUtils = new PageUtils(scoreSchoolSetSchoolList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("school:scoreSchoolSetSchool:add")
	String add(Model model){
	    return "school/school/scoreSchoolSetSchool/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("school:scoreSchoolSetSchool:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreSchoolSetDO scoreSchoolSetSchool = scoreSchoolSetSchoolService.get(id);
		model.addAttribute("scoreSchoolSetSchool", scoreSchoolSetSchool);
	    return "school/school/scoreSchoolSetSchool/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("school:scoreSchoolSetSchool:add")
	public R save( ScoreSchoolSetDO scoreSchoolSetSchool){
        scoreSchoolSetSchool.setOperator(ShiroUtils.getUserId().toString());
		if(scoreSchoolSetSchoolService.save(scoreSchoolSetSchool)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("school:scoreSchoolSetSchool:edit")
	public R update( ScoreSchoolSetDO scoreSchoolSetSchool){
	    scoreSchoolSetSchool.setOperator(ShiroUtils.getUserId().toString());
		scoreSchoolSetSchoolService.update(scoreSchoolSetSchool);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("school:scoreSchoolSetSchool:remove")
	public R remove( Long id){
		if(scoreSchoolSetSchoolService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("school:scoreSchoolSetSchool:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreSchoolSetSchoolService.batchRemove(ids);
		return R.ok();
	}
	
}
