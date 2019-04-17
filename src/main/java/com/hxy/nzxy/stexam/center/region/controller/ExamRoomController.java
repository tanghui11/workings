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

import com.hxy.nzxy.stexam.domain.ExamRoomDO;
import com.hxy.nzxy.stexam.center.region.service.ExamRoomService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考场
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:18
 */
 
@Controller
@RequestMapping("/region/examRoom")
public class ExamRoomController extends SystemBaseController{
	@Autowired
	private ExamRoomService examRoomService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("region:examRoom:examRoom")
	String ExamRoom(){
	    return "center/region/examRoom/examRoom";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:examRoom:examRoom")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ExamRoomDO> examRoomList = examRoomService.list(query);
        for (ExamRoomDO item : examRoomList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = examRoomService.count(query);
		PageUtils pageUtils = new PageUtils(examRoomList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:examRoom:add")
	String add(Model model){
	    return "center/region/examRoom/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:examRoom:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ExamRoomDO examRoom = examRoomService.get(id);
		model.addAttribute("examRoom", examRoom);
	    return "center/region/examRoom/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:examRoom:add")
	public R save( ExamRoomDO examRoom){
        examRoom.setOperator(ShiroUtils.getUserId().toString());
		if(examRoomService.save(examRoom)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:examRoom:edit")
	public R update( ExamRoomDO examRoom){
	    examRoom.setOperator(ShiroUtils.getUserId().toString());
		examRoomService.update(examRoom);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:examRoom:remove")
	public R remove( Long id){
		if(examRoomService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:examRoom:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		examRoomService.batchRemove(ids);
		return R.ok();
	}
	
}
