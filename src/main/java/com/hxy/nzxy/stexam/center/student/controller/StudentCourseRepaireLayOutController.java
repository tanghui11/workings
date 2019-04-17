package com.hxy.nzxy.stexam.center.student.controller;

import com.hxy.nzxy.stexam.center.region.service.RoomArrangeService;
import com.hxy.nzxy.stexam.center.student.service.StudentCourseService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.RoomArrangeDO;
import com.hxy.nzxy.stexam.domain.StudentCourseDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 考生课程补报-编排
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/studentCourseRepaireLayOut")
public class StudentCourseRepaireLayOutController extends SystemBaseController{
	@Autowired
	private StudentCourseService studentCourseService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private RoomArrangeService roomArrangeService;
	@GetMapping()
	@RequiresPermissions("student:studentCourseRepaireLayOut:studentCourseRepaireLayOut")
	String StudentCourse(){
	    return "center/student/studentCourseRepaireLayOut/studentCourseRepaireLayOut";
	}
	@GetMapping("/seat")
	@RequiresPermissions("student:studentCourseRepaireLayOut:studentCourseRepaireLayOut")
	String seat(){
		return "center/student/studentCourseRepaireLayOut/studentCourseRepaireLayOut2";
	}
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentCourseRepaireLayOut:studentCourseRepaireLayOut")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<Map<String, Object>> studentCourseList = studentCourseService.listRepairetLayOut(query);
        for (Map<String, Object> item : studentCourseList) {
            item.put("courseName",FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.get("courseid").toString()));
			String s = FieldDictUtil.get(getAppName(), "exa_exam_time", "segment", item.get("segment").toString());
			item.put("segmentName",s) ;
			item.put("regionName",FieldDictUtil.get(getAppName(), "reg_region_nzxy", "id", item.get("regionid").toString()));

		}
		int total = studentCourseService.countRepairetLayOut(query);
		PageUtils pageUtils = new PageUtils(studentCourseList, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/listSeat")
	@RequiresPermissions("student:studentCourseRepaireLayOut:studentCourseRepaireLayOut")
	public List<Map<String, Object>> listSeat(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<Map<String, Object>> studentCourseList = studentCourseService.listSeat(params);
		for (Map<String, Object> item : studentCourseList) {
			item.put("courseName",FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.get("courseid").toString()));
		}

		return studentCourseList;
	}
	@GetMapping("/add")
	@RequiresPermissions("student:studentCourseRepaireLayOut:add")
	String add(Model model){
	    return "center/student/studentCourseRepaireLayOut/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentCourseRepaireLayOut:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentCourseDO studentCourse = studentCourseService.get(id);
		model.addAttribute("studentCourse", studentCourse);
	    return "center/student/studentCourseRepaireLayOut/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentCourseRepaireLayOut:add")
	public R save( StudentCourseDO studentCourse){
        studentCourse.setOperator(ShiroUtils.getUserId().toString());
		if(studentCourseService.save(studentCourse)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentCourseRepaireLayOut:edit")
	public R update( StudentCourseDO studentCourse){
	    studentCourse.setOperator(ShiroUtils.getUserId().toString());
		studentCourseService.update(studentCourse);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentCourseRepaireLayOut:remove")
	public R remove( Long id){
		if(studentCourseService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentCourseRepaireLayOut:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentCourseService.batchRemove(ids);
		return R.ok();
	}
	/**
	 * 保存报考
	 */
	@ResponseBody
	@PostMapping("/saveSeat")
	@RequiresPermissions("student:studentCourseRepaireLayOut:add")
	public R save( @RequestParam Map<String, Object> params, StudentCourseDO studentCourse){
		params.put("operator",ShiroUtils.getUserId().toString());
		List<Map<String, Object>> list =	studentCourseService.selectSameTime(params);
		if(list.size()==0){
			return R.error("同一时间段只能报考一门课程!");
		}
		studentCourse.setType("d");
		studentCourse.setStatus("a");
		studentCourse.setArrangeStatus("b");
		studentCourse.setOperator(ShiroUtils.getUserId().toString());
		RoomArrangeDO room_arrangeid = roomArrangeService.get(Long.valueOf(params.get("room_arrangeid").toString()));
		room_arrangeid.setSeatEnd(room_arrangeid.getSeatEnd()+1);
		roomArrangeService.update(room_arrangeid);
		if(studentCourseService.saveSeat(params,studentCourse)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 删除
	 */
	@PostMapping( "/removeSeat")
	@ResponseBody
	@RequiresPermissions("student:studentCourseRepaireLayOut:remove")
	public R removeSeat(@RequestParam Map<String, Object> params){
		List<Map<String, Object>> list1 =	studentCourseService.selectLast(params);
		if(list1.size()==0){
			return R.error("不能删除非末尾考场的信息！");
		}
		List<Map<String, Object>> list2 =	studentCourseService.selectSeatByid(params);
		if(list2.size()==0){
			return R.error("不能删除正常报考的信息!");
		}
		RoomArrangeDO room_arrangeid = roomArrangeService.get(Long.valueOf(params.get("room_arrangeid").toString()));
		List<RoomArrangeDO> roomArrangeList= roomArrangeService.getByTimeidRoomId(room_arrangeid);
		for (RoomArrangeDO item: roomArrangeList){
			if(item.getSeatEnd()>room_arrangeid.getSeatEnd()){
				item.setSeatEnd(item.getSeatEnd()-1);
				item.setSeatStart(item.getSeatStart()-1);
				item.setOperator(ShiroUtils.getUserId().toString());
				roomArrangeService.update(item);
			}

		}
		if(room_arrangeid.getSeatEnd()-room_arrangeid.getSeatStart()==0){
			roomArrangeService.remove(Long.valueOf(params.get("room_arrangeid").toString()));
		}else{
			room_arrangeid.setSeatEnd(room_arrangeid.getSeatEnd()-1);
			room_arrangeid.setOperator(ShiroUtils.getUserId().toString());
			roomArrangeService.update(room_arrangeid);
		}

		if(studentCourseService.removeSeat(params)>0){
			return R.ok();
		}
		return R.error();
	}
}
