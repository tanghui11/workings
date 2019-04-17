package com.hxy.nzxy.stexam.center.student.controller;

import com.hxy.nzxy.stexam.center.student.service.ScoreInService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.ScoreInDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/**
 * 转入成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
 
@Controller
@RequestMapping("/student/scoreIn")
public class ScoreInController extends SystemBaseController{
	@Autowired
	private ScoreInService scoreInService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreIn:scoreIn")
	String ScoreIn(){
	    return "center/student/scoreIn/scoreIn";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreIn:scoreIn")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreInDO> scoreInList = scoreInService.list(query);
        for (ScoreInDO item : scoreInList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreInService.count(query);
		PageUtils pageUtils = new PageUtils(scoreInList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreIn:add")
	String add(Model model){
	    return "center/student/scoreIn/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreIn:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreInDO scoreIn = scoreInService.get(id);
		model.addAttribute("scoreIn", scoreIn);
	    return "center/student/scoreIn/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreIn:add")
	public R save( ScoreInDO scoreIn){
        scoreIn.setOperator(ShiroUtils.getUserId().toString());
		if(scoreInService.save(scoreIn)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreIn:edit")
	public R update( ScoreInDO scoreIn){
	    scoreIn.setOperator(ShiroUtils.getUserId().toString());
		scoreInService.update(scoreIn);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreIn:remove")
	public R audit( Long id){
		if(scoreInService.audit(id)>0){
		return R.ok();
		}
		return R.error();
	}

	
}