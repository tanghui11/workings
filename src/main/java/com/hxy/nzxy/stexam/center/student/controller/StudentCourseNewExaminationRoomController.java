package com.hxy.nzxy.stexam.center.student.controller;

import com.hxy.nzxy.stexam.center.region.service.ExamRoomService;
import com.hxy.nzxy.stexam.center.region.service.RoomArrangeService;
import com.hxy.nzxy.stexam.center.region.service.SeatArrangeService;
import com.hxy.nzxy.stexam.center.student.service.StudentCourseService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.ExamRoomDO;
import com.hxy.nzxy.stexam.domain.RoomArrangeDO;
import com.hxy.nzxy.stexam.domain.SeatArrangeDO;
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
 * 补考课程新建考场
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/studentCourseNewExaminationRoom")
public class StudentCourseNewExaminationRoomController extends SystemBaseController{
	@Autowired
	private StudentCourseService studentCourseService;
	@Autowired
	private ExamRoomService examRoomService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private RoomArrangeService roomArrangeService;
	@Autowired
	private SeatArrangeService seatArrangeService;
	@GetMapping()
	@RequiresPermissions("student:studentCourseNewExaminationRoom:studentCourseNewExaminationRoom")
	String StudentCourse(){
	    return "center/student/studentCourseNewExaminationRoom/studentCourseNewExaminationRoom";
	}

	@GetMapping("/room1")
	@RequiresPermissions("student:studentCourseNewExaminationRoom:studentCourseNewExaminationRoom")
	String StudentCourse1(){
		return "center/student/studentCourseNewExaminationRoom/studentCourseNewExaminationRoom1";
	}

	@GetMapping("/seat")
	@RequiresPermissions("student:studentCourseNewExaminationRoom:studentCourseNewExaminationRoom")
	String seat(){
		return "center/student/studentCourseNewExaminationRoom/studentCourseZuoCiBiao";
	}
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentCourseNewExaminationRoom:studentCourseNewExaminationRoom")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentCourseDO> studentCourseList = studentCourseService.list(query);
        for (StudentCourseDO item : studentCourseList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentCourseService.count(query);
		PageUtils pageUtils = new PageUtils(studentCourseList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentCourseNewExaminationRoom:add")
	String add(Model model){
	    return "center/student/studentCourseNewExaminationRoom/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentCourseNewExaminationRoom:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentCourseDO studentCourse = studentCourseService.get(id);
		model.addAttribute("studentCourse", studentCourse);
	    return "center/student/studentCourseNewExaminationRoom/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentCourseNewExaminationRoom:add")
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
	@RequiresPermissions("student:studentCourseNewExaminationRoom:edit")
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
	@RequiresPermissions("student:studentCourseNewExaminationRoom:remove")
	public R remove( Long id,String opertor){
		if(!opertor.equals(ShiroUtils.getUserId().toString())){
			return R.error("只能删除自己新建的考场！");
		}
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
	@RequiresPermissions("student:studentCourseNewExaminationRoom:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentCourseService.batchRemove(ids);
		return R.ok();
	}
//选择可用考场
	@ResponseBody
	@GetMapping("/selectRoom")
	@RequiresPermissions("student:studentCourseNewExaminationRoom:studentCourseNewExaminationRoom")
	public List<Map<String, Object>> selectRoom(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<Map<String, Object>> roomList = studentCourseService.selectRoom(params);
		return roomList;
	}
	//考场列表
	@ResponseBody
	@GetMapping("/selectExamRoom")
	@RequiresPermissions("student:studentCourseNewExaminationRoom:studentCourseNewExaminationRoom")
	public List<Map<String, Object>> selectExamRoom(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<Map<String, Object>> roomList = studentCourseService.selectExamRoomRoom(params);
		for ( Map<String, Object> item: roomList){
			item.put("regionName",FieldDictUtil.get(getAppName(), "reg_region_nzxy_a", "id", String.valueOf(item.get("regionid"))));
			item.put("segment",FieldDictUtil.get(getAppName(), "exa_exam_time", "segment", item.get("segment").toString()));
			item.put("courseName",FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.get("courseid").toString()));
		}
		return roomList;
	}
	//选择可用考场中办学点
	@ResponseBody
	@GetMapping("/selectSubmitSite")
	@RequiresPermissions("student:studentCourseNewExaminationRoom:batchRemove")
	public List<Map<String, Object>> selectSubmitSite(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<Map<String, Object>> sbmistSite = studentCourseService.selectSubmitSite(params);
		return sbmistSite;
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/saveRoom")
	@RequiresPermissions("student:studentCourseNewExaminationRoom:add")
	public R saveRoom(@RequestParam Map<String, Object> params){
		ExamRoomDO examRoom1=studentCourseService.selectRoom2(params);
		examRoom1.setRoomNo(examRoom1.getRoomNo()+1);
		examRoom1.setOperator(ShiroUtils.getUserId().toString());
		if(examRoomService.save(examRoom1)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 保存报考
	 */
	@ResponseBody
	@PostMapping("/saveSeat")
	@RequiresPermissions("student:studentCourseNewExaminationRoom:add")
	public R save( @RequestParam Map<String, Object> params, StudentCourseDO studentCourse){
		params.put("operator",ShiroUtils.getUserId().toString());
		List<Map<String, Object>> list =	studentCourseService.selectSameTime(params);
		RoomArrangeDO roomArrange=new RoomArrangeDO();
		if(list.size()==0){
			return R.error("同一时间段只能报考一门课程!");
		}
		//roomArrangeService
		List<Map<String, Object>> roomList =studentCourseService.selectExamRoomRoom(params);
		if(roomList.size()>0){
			roomArrange = roomArrangeService.get(Long.valueOf(roomList.get(roomList.size() - 1).get("id").toString()));
			roomArrange.setOperator(ShiroUtils.getUserId().toString());
			if(roomList.get(roomList.size()-1).get("exam_courseid").equals(Long.valueOf(params.get("exam_courseid").toString()))){
				//最后一次编排信息座次结束号加一
				if(roomArrange.getSeatEnd().equals("30")){
					return  R.error("座位数已满!");
				}

				roomArrange.setSeatEnd(roomArrange.getSeatEnd()+1);
				roomArrangeService.update(roomArrange);
			}else{
				//新增一次编排
				roomArrange.setSeatStart(roomArrange.getSeatStart()+1);
				roomArrange.setSeatEnd(roomArrange.getSeatEnd()+1);
				roomArrange.setCourseid(params.get("courseid").toString());
				roomArrange.setExamCourseid(Long.valueOf(params.get("exam_courseid").toString()));
				roomArrangeService.save(roomArrange);
			}

		}else{
			//新增一次编排，空考场的
			roomArrange.setOperator(ShiroUtils.getUserId().toString());
			roomArrange.setSeatStart(1);
			roomArrange.setSeatEnd(1);
			roomArrange.setRegionid(Long.valueOf(params.get("bpRegionid").toString()));
			roomArrange.setExamTimeid(Long.valueOf(params.get("exam_timeid").toString()));
			roomArrange.setCourseid(params.get("courseid").toString());
			roomArrange.setExamCourseid(Long.valueOf(params.get("exam_courseid").toString()));
			roomArrange.setExamRoomid(Long.valueOf(params.get("exam_roomid").toString()));
			roomArrange.setType("x");
			roomArrange.setExamType("x");
			roomArrange.setStatus("a");
			roomArrangeService.save(roomArrange);
		}

		studentCourse.setType("d");
		studentCourse.setStatus("a");
		studentCourse.setArrangeStatus("b");
		studentCourse.setOperator(ShiroUtils.getUserId().toString());
		params.put("room_arrangeid",roomArrange.getId()+"");
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
	@RequiresPermissions("student:studentCourseNewExaminationRoom:remove")
	public R removeSeat(@RequestParam Map<String, Object> params){
		List<Map<String, Object>> list1 =	studentCourseService.selectLast(params);
		if(list1.size()==0){
			return R.error("不能删除非末尾考场的信息！");
		}
		List<Map<String, Object>> list2 =	studentCourseService.selectSeatByid(params);
		if(list2.size()==0){
			return R.error("不能删除正常报考的信息！");
		}
		RoomArrangeDO room_arrangeid = roomArrangeService.get(Long.valueOf(params.get("room_arrangeid").toString()));
		if(room_arrangeid.getSeatEnd()-room_arrangeid.getSeatStart()==0){
			roomArrangeService.remove(Long.valueOf(params.get("room_arrangeid").toString()));
		}else{
			room_arrangeid.setSeatEnd(room_arrangeid.getSeatEnd()-1);
			room_arrangeid.setOperator(ShiroUtils.getUserId().toString());
			roomArrangeService.update(room_arrangeid);
		}
		SeatArrangeDO seatArrange = seatArrangeService.get(Long.valueOf(params.get("id").toString()));
		List<SeatArrangeDO> roomArrangeDOList=studentCourseService.selectAllSeat(params);
		for (SeatArrangeDO item:roomArrangeDOList){
			if(item.getSeq()>seatArrange.getSeq()){
				item.setSeq(item.getSeq()-1);
				seatArrangeService.update(item);
			}
		}
		if(studentCourseService.removeSeat1(params)>0){
			return R.ok();
		}
		return R.error();
	}
	@ResponseBody
	@GetMapping("/listSeat")
	@RequiresPermissions("student:studentCourseNewExaminationRoom:studentCourseNewExaminationRoom")
	public List<Map<String, Object>> listSeat(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<Map<String, Object>> studentCourseList = studentCourseService.listSeat(params);
		for (Map<String, Object> item : studentCourseList) {
			item.put("courseName",FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.get("courseid").toString()));
		}

		return studentCourseList;
	}
}
