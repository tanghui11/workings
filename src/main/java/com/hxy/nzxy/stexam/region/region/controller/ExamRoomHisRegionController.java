package com.hxy.nzxy.stexam.region.region.controller;

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

import com.hxy.nzxy.stexam.domain.ExamRoomHisDO;
import com.hxy.nzxy.stexam.region.region.service.ExamRoomHisRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考场_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:33
 */
 
@Controller
@RequestMapping("/region/examRoomHisRegion")
public class ExamRoomHisRegionController extends SystemBaseController{
	@Autowired
	private ExamRoomHisRegionService examRoomHisRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("region:examRoomHisRegion:examRoomHisRegion")
	String ExamRoomHisRegion(){
	    return "region/region/examRoomHisRegion/examRoomHisRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:examRoomHisRegion:examRoomHisRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ExamRoomHisDO> examRoomHisRegionList = examRoomHisRegionService.list(query);
        for (ExamRoomHisDO item : examRoomHisRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = examRoomHisRegionService.count(query);
		PageUtils pageUtils = new PageUtils(examRoomHisRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:examRoomHisRegion:add")
	String add(Model model){
	    return "region/region/examRoomHisRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:examRoomHisRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ExamRoomHisDO examRoomHisRegion = examRoomHisRegionService.get(id);
		model.addAttribute("examRoomHisRegion", examRoomHisRegion);
	    return "region/region/examRoomHisRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:examRoomHisRegion:add")
	public R save( ExamRoomHisDO examRoomHisRegion){
        examRoomHisRegion.setOperator(ShiroUtils.getUserId().toString());
		if(examRoomHisRegionService.save(examRoomHisRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:examRoomHisRegion:edit")
	public R update( ExamRoomHisDO examRoomHisRegion){
	    examRoomHisRegion.setOperator(ShiroUtils.getUserId().toString());
		examRoomHisRegionService.update(examRoomHisRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:examRoomHisRegion:remove")
	public R remove( Long id){
		if(examRoomHisRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:examRoomHisRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		examRoomHisRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
