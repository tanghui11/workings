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

import com.hxy.nzxy.stexam.domain.ScoreImportDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreImportRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 成绩导入临时表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
 
@Controller
@RequestMapping("/student/scoreImportRegion")
public class ScoreImportRegionController extends SystemBaseController{
	@Autowired
	private ScoreImportRegionService scoreImportRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreImportRegion:scoreImportRegion")
	String ScoreImportRegion(){
	    return "region/student/scoreImportRegion/scoreImportRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreImportRegion:scoreImportRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreImportDO> scoreImportRegionList = scoreImportRegionService.list(query);
        for (ScoreImportDO item : scoreImportRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreImportRegionService.count(query);
		PageUtils pageUtils = new PageUtils(scoreImportRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreImportRegion:add")
	String add(Model model){
	    return "region/student/scoreImportRegion/add";
	}

	@GetMapping("/edit/{kemuid}")
	@RequiresPermissions("student:scoreImportRegion:edit")
	String edit(@PathVariable("kemuid") String kemuid,Model model){
		ScoreImportDO scoreImportRegion = scoreImportRegionService.get(kemuid);
		model.addAttribute("scoreImportRegion", scoreImportRegion);
	    return "region/student/scoreImportRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreImportRegion:add")
	public R save( ScoreImportDO scoreImportRegion){
        scoreImportRegion.setOperator(ShiroUtils.getUserId().toString());
		if(scoreImportRegionService.save(scoreImportRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreImportRegion:edit")
	public R update( ScoreImportDO scoreImportRegion){
	    scoreImportRegion.setOperator(ShiroUtils.getUserId().toString());
		scoreImportRegionService.update(scoreImportRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreImportRegion:remove")
	public R remove( String kemuid){
		if(scoreImportRegionService.remove(kemuid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreImportRegion:batchRemove")
	public R remove(@RequestParam("ids[]") String[] kemuids){
		scoreImportRegionService.batchRemove(kemuids);
		return R.ok();
	}
	
}
