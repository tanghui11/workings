package com.hxy.nzxy.stexam.center.region.controller;

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
import com.hxy.nzxy.stexam.center.region.service.ExamRoomHisService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考场_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:18
 */
 
@Controller
@RequestMapping("/region/examRoomHis")
public class ExamRoomHisController extends SystemBaseController{
	@Autowired
	private ExamRoomHisService examRoomHisService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("region:examRoomHis:examRoomHis")
	String ExamRoomHis(){
	    return "center/region/examRoomHis/examRoomHis";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:examRoomHis:examRoomHis")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ExamRoomHisDO> examRoomHisList = examRoomHisService.list(query);
        for (ExamRoomHisDO item : examRoomHisList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = examRoomHisService.count(query);
		PageUtils pageUtils = new PageUtils(examRoomHisList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:examRoomHis:add")
	String add(Model model){
	    return "center/region/examRoomHis/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:examRoomHis:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ExamRoomHisDO examRoomHis = examRoomHisService.get(id);
		model.addAttribute("examRoomHis", examRoomHis);
	    return "center/region/examRoomHis/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:examRoomHis:add")
	public R save( ExamRoomHisDO examRoomHis){
        examRoomHis.setOperator(ShiroUtils.getUserId().toString());
		if(examRoomHisService.save(examRoomHis)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:examRoomHis:edit")
	public R update( ExamRoomHisDO examRoomHis){
	    examRoomHis.setOperator(ShiroUtils.getUserId().toString());
		examRoomHisService.update(examRoomHis);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:examRoomHis:remove")
	public R remove( Long id){
		if(examRoomHisService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:examRoomHis:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		examRoomHisService.batchRemove(ids);
		return R.ok();
	}
	
}
