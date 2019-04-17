package com.hxy.nzxy.stexam.center.region.controller;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.service.StudentCourseService;
import com.hxy.nzxy.stexam.domain.StudentCourseDO;
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

import com.hxy.nzxy.stexam.domain.RoomArrangeDO;
import com.hxy.nzxy.stexam.center.region.service.RoomArrangeService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考场编排
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:18
 */
 
@Controller
@RequestMapping("/region/roomArrange")
public class RoomArrangeController extends SystemBaseController{
	@Autowired
	private RoomArrangeService roomArrangeService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private StudentCourseService studentCourseService;
	@GetMapping()
	@RequiresPermissions("region:roomArrange:roomArrange")
	String RoomArrange(){
	    return "center/region/roomArrange/roomArrange";
	}
	@GetMapping("/check")
	@RequiresPermissions("region:roomArrange:roomArrange")
	String check(){
		return "center/region/roomArrange/roomArrangeCheck";
	}
	@ResponseBody
	@GetMapping("/listCheck")
	@RequiresPermissions("region:roomArrange:roomArrange")
	public List<Map<String, Object>> listCheck(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<Map<String, Object>> roomArrangeList = roomArrangeService.listCheck(params);
		return roomArrangeList;
	}
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:roomArrange:roomArrange")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RoomArrangeDO> roomArrangeList = roomArrangeService.list(query);
        for (RoomArrangeDO item : roomArrangeList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setType(FieldDictUtil.get(getAppName(), "reg_room_arrange", "type", item.getType()));
			item.setExamType(FieldDictUtil.get(getAppName(), "reg_room_arrange", "exam_type", item.getExamType()));
			item.setStatus(FieldDictUtil.get(getAppName(), "reg_room_arrange", "status", item.getStatus()));
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));

        }
		int total = roomArrangeService.count(query);
		PageUtils pageUtils = new PageUtils(roomArrangeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:roomArrange:add")
	String add(Model model){
	    return "center/region/roomArrange/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:roomArrange:edit")
	String edit(@PathVariable("id") Long id,Model model){
		RoomArrangeDO roomArrange = roomArrangeService.get(id);
		model.addAttribute("roomArrange", roomArrange);
	    return "center/region/roomArrange/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:roomArrange:add")
	public R save( RoomArrangeDO roomArrange){
        roomArrange.setOperator(ShiroUtils.getUserId().toString());
		if(roomArrangeService.save(roomArrange)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:roomArrange:edit")
	public R update( RoomArrangeDO roomArrange){
	    roomArrange.setOperator(ShiroUtils.getUserId().toString());
		roomArrangeService.update(roomArrange);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:roomArrange:remove")
	public R remove( Long id){
		if(roomArrangeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:roomArrange:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		roomArrangeService.batchRemove(ids);
		return R.ok();
	}

	//考场编排确认</br>
	//输入值：考区编号,考试任务编号, status 确认状态，操作员,ref 返回值,ref 返回消息
	@RequestMapping( "/roomArrangeConfirm")
	@ResponseBody
	public R roomArrangeConfirm( @RequestParam Map<String, Object> params){
		if(roomArrangeService.updateRoomArrangeConfirm(params)>0){
			return R.ok();
		}
		return R.error();
	}
}
