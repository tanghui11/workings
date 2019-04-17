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

import com.hxy.nzxy.stexam.domain.ScoreTempDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreTempRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 登分表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
 
@Controller
@RequestMapping("/student/scoreTempRegion")
public class ScoreTempRegionController extends SystemBaseController{
	@Autowired
	private ScoreTempRegionService scoreTempRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreTempRegion:scoreTempRegion")
	String ScoreTempRegion(){
	    return "region/student/scoreTempRegion/scoreTempRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreTempRegion:scoreTempRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreTempDO> scoreTempRegionList = scoreTempRegionService.list(query);
        for (ScoreTempDO item : scoreTempRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreTempRegionService.count(query);
		PageUtils pageUtils = new PageUtils(scoreTempRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreTempRegion:add")
	String add(Model model){
	    return "region/student/scoreTempRegion/add";
	}

	@GetMapping("/edit/{seatArrangeid}")
	@RequiresPermissions("student:scoreTempRegion:edit")
	String edit(@PathVariable("seatArrangeid") Long seatArrangeid,Model model){
		ScoreTempDO scoreTempRegion = scoreTempRegionService.get(seatArrangeid);
		model.addAttribute("scoreTempRegion", scoreTempRegion);
	    return "region/student/scoreTempRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreTempRegion:add")
	public R save( ScoreTempDO scoreTempRegion){
        scoreTempRegion.setOperator(ShiroUtils.getUserId().toString());
		if(scoreTempRegionService.save(scoreTempRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreTempRegion:edit")
	public R update( ScoreTempDO scoreTempRegion){
	    scoreTempRegion.setOperator(ShiroUtils.getUserId().toString());
		scoreTempRegionService.update(scoreTempRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreTempRegion:remove")
	public R remove( Long seatArrangeid){
		if(scoreTempRegionService.remove(seatArrangeid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreTempRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] seatArrangeids){
		scoreTempRegionService.batchRemove(seatArrangeids);
		return R.ok();
	}
	
}
