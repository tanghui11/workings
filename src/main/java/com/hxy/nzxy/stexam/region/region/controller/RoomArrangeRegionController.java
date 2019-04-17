package com.hxy.nzxy.stexam.region.region.controller;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.service.StudentCourseService;
import com.hxy.nzxy.stexam.domain.*;
import com.hxy.nzxy.stexam.region.region.service.SeatArrangeRegionService;
import com.hxy.nzxy.stexam.region.student.service.StudentCourseRegionService;
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

import com.hxy.nzxy.stexam.region.region.service.RoomArrangeRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考场编排
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
 
@Controller
@RequestMapping("/region/roomArrangeRegion")
public class RoomArrangeRegionController extends SystemBaseController{
	@Autowired
	private RoomArrangeRegionService roomArrangeRegionService;
	@Autowired
	private SeatArrangeRegionService  seatArrangeRegionService;
	@Autowired
	private StudentCourseRegionService studentCourseRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("region:roomArrangeRegion:roomArrangeRegion")
	String RoomArrangeRegion(){
	    return "region/region/roomArrangeRegion/roomArrangeRegion";
	}
	@GetMapping("/region1")
	@RequiresPermissions("region:roomArrangeRegion:roomArrangeRegion")
	String RoomArrangeRegion1(){
		return "region/region/roomArrangeRegion/roomArrangeRegion1";
	}
	@GetMapping("region2")
	@RequiresPermissions("region:roomArrangeRegion:roomArrangeRegion")
	String RoomArrangeRegion2(){
		return "region/region/roomArrangeRegion/roomArrangeRegion2";
	}
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:roomArrangeRegion:roomArrangeRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RoomArrangeDO> roomArrangeRegionList = roomArrangeRegionService.list(query);
        for (RoomArrangeDO item : roomArrangeRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = roomArrangeRegionService.count(query);
		PageUtils pageUtils = new PageUtils(roomArrangeRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("region:roomArrangeRegion:add")
	String add(Model model){
	    return "region/region/roomArrangeRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:roomArrangeRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		RoomArrangeDO roomArrangeRegion = roomArrangeRegionService.get(id);
		model.addAttribute("roomArrangeRegion", roomArrangeRegion);
	    return "region/region/roomArrangeRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:roomArrangeRegion:add")
	public R save( RoomArrangeDO roomArrangeRegion){
        roomArrangeRegion.setOperator(ShiroUtils.getUserId().toString());
		if(roomArrangeRegionService.save(roomArrangeRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:roomArrangeRegion:edit")
	public R update( RoomArrangeDO roomArrangeRegion){
	    roomArrangeRegion.setOperator(ShiroUtils.getUserId().toString());
		roomArrangeRegionService.update(roomArrangeRegion);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:roomArrangeRegion:remove")
	public R remove( Long id){
		if(roomArrangeRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:roomArrangeRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		roomArrangeRegionService.batchRemove(ids);
		return R.ok();
	}

	//根据考试任务 查询所有的开考课程
	@ResponseBody
	@GetMapping("/listExamCourse")
	@RequiresPermissions("region:roomArrangeRegion:roomArrangeRegion")
	public PageUtils listExamCourse(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<RoomArrangeDO> roomArrangeRegionList = roomArrangeRegionService.list(query);
		for (RoomArrangeDO item : roomArrangeRegionList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = roomArrangeRegionService.count(query);
		PageUtils pageUtils = new PageUtils(roomArrangeRegionList, total);
		return pageUtils;
	}

	//按照考区编号和开考课程编号获取报考课程信息</br>
	//输入值：考区编号,考试时间编号,ref 返回值,ref 返回消息</br>
	//返回数据集：课程代码,课程名称,报考人数

	@GetMapping( "/getStudentCourses")
	@ResponseBody
	public List<ExamCourseDO> getStudentCourses(@RequestParam Map<String, Object> params){
		//查询列表数据
		//Query query = new Query(params);
		params.put("regionid",ShiroUtils.getUser().getWorkerid());
      List<ExamCourseDO>  list=roomArrangeRegionService.ExamArrange_GetStudentCourses(params);
		for (ExamCourseDO item : list) {
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
		}
		return list;
	}
	//按照考区编号、考试任务编号查找本次可用的考点</br>
	//输入值：考区编号,考试任务编号,关键字,ref 返回值,ref 返回消息</br>
	//返回数据集：编号,考点名称"
	@GetMapping( "/getExamSites")
	@ResponseBody
	public List<Map<String, Object>> getExamSites(@RequestParam Map<String, Object> params){
		//查询列表数据
		//Query query = new Query(params);
		if(params.get("regionid")==null||params.get("regionid").equals("")){
			params.put("regionid",ShiroUtils.getUser().getWorkerid());
   		}
		List<Map<String, Object>> list=roomArrangeRegionService.ExamArrange_GetExamSites(params);
		return list;
	}
//按照考区编号及考试任务编号查找考场信息</br>
//输入值：考区编号,考试时间编号,是否使用末尾考场标志,ref 返回值,ref 返回消息</br>
//回数据集：编号,考点代码,考点名称,考场编号,剩余座位数")]

	@GetMapping( "/getExamRooms")
	@ResponseBody
	public List<ExamRoomDO>  getExamRooms(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("regionid",ShiroUtils.getUser().getWorkerid());
		List<ExamRoomDO>  list=roomArrangeRegionService.ExamArrange_GetExamRoomsByRegionid_ExamTaskid(params);


		return list;
	}

 // 安排上下午都有考试的考生</br>
 // 输入值：考生类别,考试类别,考区编号,考试任务编号,考试时间编号,每考场最大课程数,每考点保留考场百分数,最少学生数,每个考场最多空余座位数,是否使用末尾考场,ref 返回值,ref 返回消息
 @GetMapping( "/roomArrangeAuto1")
 @ResponseBody
  public String roomArrangeAuto1(@RequestParam Map<String, Object> params){
	 String strMsg ;
	 String outMsg ;

	 params.put("regionid",ShiroUtils.getUser().getWorkerid());
	 List<ExamCourseDO>  list1=roomArrangeRegionService.ExamArrange_GetStudentExamCourses1(params);
	 if (list1 == null || list1.size() == 0)
	 {

		outMsg = "没有找到上下午都有考试的考生报考信息！";
		 return outMsg;
	 }
	 List<ExamRoomDO> list2= roomArrangeRegionService.ExamArrange_GetExamRooms1(params);
	 if (list2== null || list2.size() == 0)
	 {

		 outMsg = "没有找到可可用的考场！";
		 return outMsg;
	 }
	 //考场计数器
	 int intExamRoomCount = 0;
	 for (ExamCourseDO item : list1){
		 int intExamRoomSum=Integer.valueOf(item.getNum());
	 	params.put("exam_courseid",item.getExamCourseid());
		 List<ExamCourseDO> list3= roomArrangeRegionService.ExamArrange_GetStudentCourses1(params);
		 if (list3== null || list3.size() == 0)
		 {

			 outMsg = "没有找到上下午都有考试的考生报考信息！";
			 return outMsg;
		 }

		 //学生计数器
		 int intStudentNum = list3.size();
		 int intStudentSum = list3.size();
		 int intStudentCourseCount = 0;
		 while (intStudentNum != 0 && intExamRoomCount <= intExamRoomSum)
		 {
			 //如果考生循环完终止程序
			 if (intStudentCourseCount >= intStudentSum)
			 {
				 break;
			 }
			 //插入考场信息
			 RoomArrangeDO daoRoom = new RoomArrangeDO();
			 daoRoom.setRegionid(Long.valueOf(params.get("regionid").toString()));
			 daoRoom.setExamTimeid(Long.valueOf(params.get("exam_timeid").toString()));
			 daoRoom.setExamRoomid(list2.get(intExamRoomCount).getId());
			 daoRoom.setExamCourseid(Long.valueOf(item.getExamCourseid()));
			 daoRoom.setCourseid(item.getCourseid());
			 daoRoom.setType("x");
			 daoRoom.setExamType("x") ;

			 strMsg = "考场计数器" + intExamRoomCount;
			 //如果考场座位数大于剩余学生人数，结束座位号为剩余学生人数
			 //如果考场座位数小于剩余学生人数，结束座位号为考场座位数
			 if (intStudentNum - Integer.valueOf(list2.get(intExamRoomCount).getNum()) >= 0)
			 {
				 daoRoom.setSeatStart(1);
				 daoRoom.setSeatEnd(Integer.valueOf(list2.get(intExamRoomCount).getNum()));
				 intStudentNum -= Integer.valueOf(list2.get(intExamRoomCount).getNum());
			 }
			 else
			 {
				 daoRoom.setSeatStart(1);
				 daoRoom.setSeatEnd(intStudentNum);
				 intStudentNum = 0;
			 }
			 daoRoom.setOperator(ShiroUtils.getUserId().toString());
			 daoRoom.setStatus("a");
			 int save = roomArrangeRegionService.save(daoRoom);

			 //插入座次信息
			 for (int i = daoRoom.getSeatStart(); i <= daoRoom.getSeatEnd(); i++)
			 {
				 SeatArrangeDO daoSeat = new SeatArrangeDO();
				 daoSeat.setRoomArrangeid(daoRoom.getId());
				 daoSeat.setStudentCourseid(list3.get(intStudentCourseCount).getId());
				 daoSeat.setExamCourseid(Long.valueOf(item.getExamCourseid()));
				 daoSeat.setSeq(i);
				 daoSeat.setOperator(ShiroUtils.getUserId().toString());
				 seatArrangeRegionService.save(daoSeat);
				 StudentCourseDO  studentCourseDO=new StudentCourseDO();
				 studentCourseDO.setArrangeStatus("b");
				 studentCourseDO.setId(list3.get(intStudentCourseCount).getId());
				 studentCourseRegionService.update(studentCourseDO);
				 intStudentCourseCount++;
			 }
			 intExamRoomCount++;
		 }
		 strMsg = "安排上下午都有考试的考生成功！";
	 }
		return "安排上下午都有考试的考生成功！";
 }
//	已安排课程补上剩余人数</br>
//	输入值：考区编号,考试任务编号,考试时间编号,每考场最大课程数,每考点保留考场百分数,最少学生数,每个考场最多空余座位数,ref 返回值,ref 返回消息")
@GetMapping( "/roomArrangeAuto2")
@ResponseBody
public String RoomArrangeAuto2(@RequestParam Map<String, Object> params){
	String strMsg ;
	String outMsg ;
	try
	{
	params.put("regionid",ShiroUtils.getUser().getWorkerid());
	List<ExamCourseDO>  list1=roomArrangeRegionService.ExamArrange_GetStudentExamCourses2(params);
	if (list1 == null || list1.size() == 0)
	{
		outMsg = "没有找到考生报考课程信息！";
		return outMsg;
	}
/*	List<ExamRoomDO> list2= roomArrangeRegionService.ExamArrange_GetExamRooms2(params);
	if (list2== null || list2.size() == 0)
	{

			break;
	}*/
	//考场计数器
	for (ExamCourseDO item : list1){
		int intExamRoomCount = 0;

		params.put("exam_courseid",item.getExamCourseid());
		List<ExamRoomDO> list2= roomArrangeRegionService.ExamArrange_GetExamRooms2(params);
		if (list2== null || list2.size() == 0)
		{

			break;
		}
		List<ExamCourseDO> list3= roomArrangeRegionService.ExamArrange_GetStudentCourses2(params);
		if (list3== null || list3.size() == 0)
		{
			continue;
		}
		//考生计数器
		int intStudentNum = list3.size();
		int intStudentSum = list3.size();
		int intStudentCourseCount = 0;
		for (ExamRoomDO drRoom :list2)
		{
			//如果考生循环完终止程序
			if (intStudentCourseCount >= intStudentSum)
			{
				break;
			}
			String strRoomArrangeid ="";
			int intSeatStart = 0;
			int intSeatEnd = 0;
			//如果有末尾考场，不需要新建考场，如果没有末尾考场，则新建一个考场
			if (drRoom.getRoomArrangeid() != "")
			{

				strRoomArrangeid = drRoom.getRoomArrangeid();
				intSeatStart =Integer.valueOf(drRoom.getSeatNum())-Integer.valueOf(drRoom.getNum())+1;
				//如果剩余人数大于末尾考场空座位数，结束座位号为：座位数，反之为：座位起始号+剩余人数
				if (intStudentNum - Integer.valueOf(drRoom.getNum())>= 0)
				{
					intSeatEnd = Integer.valueOf(drRoom.getSeatNum());
					intStudentNum -= Integer.valueOf(drRoom.getNum());
				}
				else
				{
					intSeatEnd = intSeatStart + intStudentNum - 1;
					intStudentNum = 0;
				}
				//修改考场安排座次号
				RoomArrangeDO daoUpgRoom = new RoomArrangeDO();
				daoUpgRoom.setId(Long.valueOf(strRoomArrangeid));
				daoUpgRoom.setSeatEnd(intSeatEnd);
				daoUpgRoom.setOperator(ShiroUtils.getUserId().toString());
				roomArrangeRegionService.update(daoUpgRoom);

			}
			else
			{
				RoomArrangeDO daoRoom = new RoomArrangeDO();
				daoRoom.setRegionid(Long.valueOf(ShiroUtils.getUser().getWorkerid()));
				daoRoom.setExamTimeid(Long.valueOf(params.get("exam_timeid").toString()));
				daoRoom.setExamRoomid(drRoom.getId());
				daoRoom.setExamCourseid(Long.valueOf(item.getExamCourseid())) ;
				daoRoom.setCourseid(item.getCourseid());
				daoRoom.setType("x");
				daoRoom.setExamType("x") ;
				//如果剩余考生人数
				if (intStudentNum - Integer.valueOf(drRoom.getName()) >= 0)
				{
					intSeatStart = 1;
					intSeatEnd =Integer.valueOf(drRoom.getSeatNum());
					intStudentNum -=Integer.valueOf(drRoom.getNum());
				}
				else
				{
					intSeatStart = 1;
					intSeatEnd = intStudentNum;
					intStudentNum = 0;
				}
				daoRoom.setSeatStart(intSeatStart);
				daoRoom.setSeatEnd(intSeatEnd);
				daoRoom.setOperator(ShiroUtils.getUserId().toString());
				daoRoom.setStatus("a");
				int save = roomArrangeRegionService.save(daoRoom);

				strRoomArrangeid = daoRoom.getId()+"";
			}
			//插入座次信息
			for (int i = intSeatStart; i <= intSeatEnd; i++)
			{
				SeatArrangeDO daoSeat = new SeatArrangeDO();
				daoSeat.setRoomArrangeid(Long.valueOf(strRoomArrangeid));
				daoSeat.setStudentCourseid(list3.get(intStudentCourseCount).getId());
				daoSeat.setExamCourseid(Long.valueOf(params.get("exam_courseid").toString()));
				daoSeat.setSeq(i) ;
				daoSeat.setOperator(ShiroUtils.getUserId().toString());
				seatArrangeRegionService.save(daoSeat);
				StudentCourseDO  studentCourseDO=new StudentCourseDO();
				studentCourseDO.setArrangeStatus("b");
				studentCourseDO.setId(list3.get(intStudentCourseCount).getId());
				studentCourseRegionService.update(studentCourseDO);
				//循环考生
				intStudentCourseCount++;
			}
		}
	}
	outMsg = "已安排课程补上剩余人数成功！";
}
            catch (Exception ex)
	{

		outMsg = "已安排课程补上剩余考生失败！\n错误原因：" + ex.getMessage();
	}
	return outMsg;
}
//安排剩余课程</br>
// 输入值：考区编号,考试任务编号,考试时间编号,每考场最大课程数,每考点保留考场百分数,最少学生数,每个考场最多空余座位数,ref 返回值,ref 返回消息
@GetMapping( "/roomArrangeAuto3")
@ResponseBody
public String RoomArrangeAuto3(@RequestParam Map<String, Object> params){
	String strMsg ;
	String outMsg ;
	try
	{
		params.put("regionid",ShiroUtils.getUser().getWorkerid());
		List<ExamCourseDO>  list1=roomArrangeRegionService.ExamArrange_GetStudentExamCourses3(params);
		if (list1 == null || list1.size() == 0)
		{
			outMsg = "没有找到考生报考课程信息！";
			return outMsg;
		}
/*	List<ExamRoomDO> list2= roomArrangeRegionService.ExamArrange_GetExamRooms2(params);
	if (list2== null || list2.size() == 0)
	{

			break;
	}*/
		//循环剩余课程
		for (ExamCourseDO item : list1){
			String strExamCourseid=item.getExamCourseid();
			params.put("exam_courseid",strExamCourseid);
			List<ExamRoomDO> list2= roomArrangeRegionService.ExamArrange_GetExamRooms3(params);
			if (list2== null || list2.size() == 0)
			{

				outMsg = "没有找到可用的考场信息！";
				return outMsg;
			}
			List<ExamCourseDO> list3= roomArrangeRegionService.ExamArrange_GetStudentCourses3(params);
			if (list3== null || list3.size() == 0)
			{
				continue;
			}
			//考生计数器
			int intStudentNum = list3.size();
			int intStudentSum = list3.size();
			int intStudentCourseCount = 0;
			for (ExamRoomDO drRoom :list2)
			{
				//如果考生循环完终止程序
				if (intStudentCourseCount >= intStudentSum)
				{
					break;
				}

				//如果剩余座位数小于待排考生数，重新找考场
				if (drRoom.getRoomArrangeid() != "" && Integer.valueOf(drRoom.getNum()) < intStudentSum)
				{
					continue;
				}
				//验证一个考场中已安排的课程是否大于指定数量
				params.put("exam_roomid",drRoom.getId());
				List<ExamCourseDO> list4=	roomArrangeRegionService.ExamArrange_GetExamRoomsCourseCount(params);
				if (list4!= null &&Integer.valueOf(list4.get(0).getNum())>=Integer.valueOf(params.get("inMaxExamCourse").toString()))
				{
					continue;
				}


				String strRoomArrangeid ="";
				int intSeatStart = 0;
				int intSeatEnd = 0;
				//插入考场信息，如果是新考场，开始座位号从1开始，如果是末尾考场，开始座位号从原开始号+1开始。
				RoomArrangeDO daoRoom = new RoomArrangeDO();
				daoRoom.setRegionid(Long.valueOf(ShiroUtils.getUser().getWorkerid()));
				daoRoom.setExamTimeid(Long.valueOf(params.get("exam_timeid").toString()));
				daoRoom.setExamRoomid(drRoom.getId());
				daoRoom.setExamCourseid(Long.valueOf(strExamCourseid));
				daoRoom.setCourseid(item.getCourseid());

				daoRoom.setType("x");
				daoRoom.setExamType("x");

				//如果有末尾考场，不需要新建考场，如果没有末尾考场，则新建一个考场
				if (drRoom.getRoomArrangeid() != "")
				{
					//strRoomArrangeid = drRoom.getRoomArrangeid();
					intSeatStart =Integer.valueOf(drRoom.getSeatNum())-Integer.valueOf(drRoom.getNum())+1;
					//如果剩余人数大于末尾考场空座位数，结束座位号为：座位数，反之为：座位起始号+剩余人数
					if (intStudentNum - Integer.valueOf(drRoom.getNum())>= 0)
					{
						intSeatEnd = Integer.valueOf(drRoom.getSeatNum());
						intStudentNum -= Integer.valueOf(drRoom.getNum());
					}
					else
					{
						intSeatEnd = intSeatStart + intStudentNum - 1;
						intStudentNum = 0;
					}
					/*//修改考场安排座次号
					RoomArrangeDO daoUpgRoom = new RoomArrangeDO();
					daoUpgRoom.setId(Long.valueOf(strRoomArrangeid));
					daoUpgRoom.setSeatEnd(intSeatEnd);
					daoUpgRoom.setOperator(ShiroUtils.getUserId().toString());
					roomArrangeRegionService.update(daoUpgRoom);*/

				}
				else
				{
					/*RoomArrangeDO daoRoom = new RoomArrangeDO();
					daoRoom.setRegionid(Long.valueOf(ShiroUtils.getUser().getWorkerid()));
					daoRoom.setExamTimeid(Long.valueOf(params.get("exam_timeid").toString()));
					daoRoom.setExamRoomid(drRoom.getId());
					daoRoom.setExamCourseid(Long.valueOf(item.getExamCourseid())) ;
					daoRoom.setType("x");
					daoRoom.setExamType("x") ;*/
					intSeatStart = 1;
					//剩余考生人数是否大于开场可容纳人数
					if (intStudentNum - Integer.valueOf(drRoom.getNum()) >= 0)
					{
						intSeatStart = 1;
						intSeatEnd =Integer.valueOf(drRoom.getSeatNum());
						intStudentNum -=Integer.valueOf(drRoom.getNum());
					}
					else
					{
						intSeatEnd = intStudentNum;
						intStudentNum = 0;
					}

				}
				daoRoom.setSeatStart(intSeatStart);
				daoRoom.setSeatEnd(intSeatEnd);
				daoRoom.setOperator(ShiroUtils.getUserId().toString());
				daoRoom.setStatus("a");
				int save = roomArrangeRegionService.save(daoRoom);
				strRoomArrangeid = daoRoom.getId()+"";
				//插入座次信息
				for (int i = intSeatStart; i <= intSeatEnd; i++)
				{
					SeatArrangeDO daoSeat = new SeatArrangeDO();
					daoSeat.setRoomArrangeid(Long.valueOf(strRoomArrangeid));
					daoSeat.setStudentCourseid(list3.get(intStudentCourseCount).getId());
					daoSeat.setExamCourseid(Long.valueOf(params.get("exam_courseid").toString()));
					daoSeat.setSeq(i) ;
					daoSeat.setOperator(ShiroUtils.getUserId().toString());
					seatArrangeRegionService.save(daoSeat);
					StudentCourseDO  studentCourseDO=new StudentCourseDO();
					studentCourseDO.setArrangeStatus("b");
					studentCourseDO.setId(list3.get(intStudentCourseCount).getId());
					studentCourseRegionService.update(studentCourseDO);
					//循环考生
					intStudentCourseCount++;
				}
			}
		}
		outMsg = "安排剩余课程成功！";
	}
	catch (Exception ex)
	{

		outMsg = "安排剩余课程失败1！\\n错误原因：" + ex.getMessage();
	}
	return outMsg;
}
//安排一门课程到指定考点
// 输入值：考区编号,考试任务编号,考试时间编号,每考场最大课程数,每考点保留考场百分数,最少学生数,每个考场最多空余座位数,ref 返回值,ref 返回消息
	@GetMapping( "/roomArrangeAuto4")
	@ResponseBody
	public String RoomArrangeAuto4(@RequestParam Map<String, Object> params){
		String strMsg ;
		String outMsg ;
		try
		{
			params.put("regionid",ShiroUtils.getUser().getWorkerid());
			List<ExamCourseDO>  list1=roomArrangeRegionService.ExamArrange_GetStudentExamCourses4(params);
			if (list1 == null || list1.size() == 0)
			{
				outMsg = "没有找到考生报考课程信息！";
				return outMsg;
			}
/*	List<ExamRoomDO> list2= roomArrangeRegionService.ExamArrange_GetExamRooms2(params);
	if (list2== null || list2.size() == 0)
	{

			break;
	}*/
			//循环剩余课程
			for (ExamCourseDO item : list1){
				String strExamCourseid=item.getExamCourseid();
				params.put("exam_courseid",strExamCourseid);
				List<ExamRoomDO> list2= roomArrangeRegionService.ExamArrange_GetExamRooms4(params);
				if (list2== null || list2.size() == 0)
				{

					outMsg = "没有找到可用的考场信息！";
					return outMsg;
				}
				List<ExamCourseDO> list3= roomArrangeRegionService.ExamArrange_GetStudentCourses4(params);
				if (list3== null || list3.size() == 0)
				{
					continue;
				}
				//考生计数器
				int intStudentNum = list3.size();
				int intStudentSum = list3.size();
				int intStudentCourseCount = 0;
				for (ExamRoomDO drRoom :list2)
				{
					//如果考生循环完终止程序
					if (intStudentCourseCount >= intStudentSum)
					{
						break;
					}

					//如果剩余座位数小于待排考生数，重新找考场
					if (drRoom.getRoomArrangeid() != "" && Integer.valueOf(drRoom.getNum()) < intStudentSum)
					{
						continue;
					}
					//验证一个考场中已安排的课程是否大于指定数量
					params.put("exam_roomid",drRoom.getId());
					List<ExamCourseDO> list4=	roomArrangeRegionService.ExamArrange_GetExamRoomsCourseCount(params);
					if (list4!= null &&Integer.valueOf(list4.get(0).getNum())>=Integer.valueOf(params.get("inMaxExamCourse").toString()))
					{
						continue;
					}


					String strRoomArrangeid ="";
					int intSeatStart = 0;
					int intSeatEnd = 0;
					//插入考场信息，如果是新考场，开始座位号从1开始，如果是末尾考场，开始座位号从原开始号+1开始。
					RoomArrangeDO daoRoom = new RoomArrangeDO();
					daoRoom.setRegionid(Long.valueOf(ShiroUtils.getUser().getWorkerid()));
					daoRoom.setExamTimeid(Long.valueOf(params.get("exam_timeid").toString()));
					daoRoom.setExamRoomid(drRoom.getId());
					daoRoom.setExamCourseid(Long.valueOf(strExamCourseid));
					daoRoom.setCourseid(item.getCourseid());
					daoRoom.setType("x");
					daoRoom.setExamType("x");

					//如果有末尾考场，不需要新建考场，如果没有末尾考场，则新建一个考场
					if (drRoom.getRoomArrangeid() != "")
					{
						//strRoomArrangeid = drRoom.getRoomArrangeid();
						intSeatStart =Integer.valueOf(drRoom.getSeatNum())-Integer.valueOf(drRoom.getNum())+1;
						//如果剩余人数大于末尾考场空座位数，结束座位号为：座位数，反之为：座位起始号+剩余人数
						if (intStudentNum - Integer.valueOf(drRoom.getNum())>= 0)
						{
							intSeatEnd = Integer.valueOf(drRoom.getSeatNum());
							intStudentNum -= Integer.valueOf(drRoom.getNum());
						}
						else
						{
							intSeatEnd = intSeatStart + intStudentNum - 1;
							intStudentNum = 0;
						}
					}
					else
					{
						intSeatStart = 1;
						//剩余考生人数是否大于开场可容纳人数
						if (intStudentNum - Integer.valueOf(drRoom.getNum()) >= 0)
						{
							intSeatEnd =Integer.valueOf(drRoom.getSeatNum());
							intStudentNum -=Integer.valueOf(drRoom.getNum());
						}
						else
						{
							intSeatEnd = intStudentNum;
							intStudentNum = 0;
						}
					}
					daoRoom.setSeatStart(intSeatStart);
					daoRoom.setSeatEnd(intSeatEnd);
					daoRoom.setOperator(ShiroUtils.getUserId().toString());
					daoRoom.setStatus("a");
					int save = roomArrangeRegionService.save(daoRoom);

					strRoomArrangeid = daoRoom.getId()+"";
					//插入座次信息
					for (int i = intSeatStart; i <= intSeatEnd; i++)
					{
						SeatArrangeDO daoSeat = new SeatArrangeDO();
						daoSeat.setRoomArrangeid(Long.valueOf(strRoomArrangeid));
						daoSeat.setStudentCourseid(list3.get(intStudentCourseCount).getId());
						daoSeat.setExamCourseid(Long.valueOf(params.get("exam_courseid").toString()));
						daoSeat.setSeq(i) ;
						daoSeat.setOperator(ShiroUtils.getUserId().toString());
						seatArrangeRegionService.save(daoSeat);
						StudentCourseDO  studentCourseDO=new StudentCourseDO();
						studentCourseDO.setArrangeStatus("b");
						studentCourseDO.setId(list3.get(intStudentCourseCount).getId());
						studentCourseRegionService.update(studentCourseDO);

						//循环考生
						intStudentCourseCount++;
					}
				}
			}
			outMsg = "安排一门课程到一个考点成功！！";
		}
		catch (Exception ex)
		{

			outMsg = "安排一门课程到一个考点失败！\\n错误原因：" + ex.getMessage();
		}
		return outMsg;
	}
//"安排一门课程到指定考场</br>
// 输入值：考区编号,考试任务编号,考试时间编号,每考场最大课程数,每考点保留考场百分数,最少学生数,每个考场最多空余座位数,课程代码,ref 返回值,ref 返回消息
//安排一门课程到指定考点
// 输入值：考区编号,考试任务编号,考试时间编号,每考场最大课程数,每考点保留考场百分数,最少学生数,每个考场最多空余座位数,ref 返回值,ref 返回消息
@GetMapping( "/roomArrangeAuto5")
@ResponseBody
public String RoomArrangeAuto5(@RequestParam Map<String, Object> params){
	String strMsg ;
	String outMsg ;

	try
	{
		params.put("regionid",ShiroUtils.getUser().getWorkerid());

		List<ExamCourseDO>  list1=roomArrangeRegionService.ExamArrange_GetStudentExamCourses5(params);
		if (list1 == null || list1.size() == 0)
		{
			outMsg = "没有找到考生报考课程信息！";
			return outMsg;
		}

		//循环剩余课程
		for (ExamCourseDO item : list1){
			String strExamCourseid=item.getExamCourseid();
			params.put("exam_courseid",strExamCourseid);
			List<ExamRoomDO> list2= roomArrangeRegionService.ExamArrange_GetExamRooms5(params);
			if (list2== null || list2.size() == 0)
			{

				outMsg = "没有找到可用的考场信息！";
				return outMsg;
			}
			List<ExamCourseDO> list3= roomArrangeRegionService.ExamArrange_GetStudentCourses5(params);
			if (list3== null || list3.size() == 0)
			{
				continue;
			}
			//考生计数器
			int intStudentNum = list3.size();
			int intStudentSum = list3.size();
			int intStudentCourseCount = 0;
			for (ExamRoomDO drRoom :list2)
			{
				//如果考生循环完终止程序
				if (intStudentCourseCount >= intStudentSum)
				{
					break;
				}

				//验证一个考场中已安排的课程是否大于指定数量
				params.put("exam_roomid",drRoom.getId());
				List<ExamCourseDO> list4=	roomArrangeRegionService.ExamArrange_GetExamRoomsCourseCount(params);
				if (list4!= null &&Integer.valueOf(list4.get(0).getNum())>=Integer.valueOf(params.get("inMaxExamCourse").toString()))
				{
					continue;
				}


				String strRoomArrangeid ="";
				int intSeatStart = 0;
				int intSeatEnd = 0;
				//插入考场信息，如果是新考场，开始座位号从1开始，如果是末尾考场，开始座位号从原开始号+1开始。
				RoomArrangeDO daoRoom = new RoomArrangeDO();
				daoRoom.setRegionid(Long.valueOf(ShiroUtils.getUser().getWorkerid()));
				daoRoom.setExamTimeid(Long.valueOf(params.get("exam_timeid").toString()));
				daoRoom.setExamRoomid(drRoom.getId());
				daoRoom.setExamCourseid(Long.valueOf(strExamCourseid));
				daoRoom.setCourseid(item.getCourseid());
				daoRoom.setType("x");
				daoRoom.setExamType("x");

				//如果有末尾考场，不需要新建考场，如果没有末尾考场，则新建一个考场
				if (drRoom.getRoomArrangeid() != "")
				{
					//strRoomArrangeid = drRoom.getRoomArrangeid();
					intSeatStart =Integer.valueOf(drRoom.getSeatNum())-Integer.valueOf(drRoom.getNum())+1;
					//剩余人数是否大于考场可容纳人数
					if (intStudentNum - Integer.valueOf(drRoom.getNum())>= 0)
					{
						intSeatEnd = Integer.valueOf(drRoom.getSeatNum());
						intStudentNum -= Integer.valueOf(drRoom.getNum());
					}
					else
					{
						intSeatEnd = intSeatStart + intStudentNum - 1;
						intStudentNum = 0;
					}
				}
				else
				{
					intSeatStart = 1;
					//剩余考生人数是否大于开场可容纳人数
					if (intStudentNum - Integer.valueOf(drRoom.getNum()) >= 0)
					{
						intSeatEnd =Integer.valueOf(drRoom.getSeatNum());
						intStudentNum -=Integer.valueOf(drRoom.getNum());
					}
					else
					{
						intSeatEnd = intStudentNum;
						intStudentNum = 0;
					}
				}
				daoRoom.setSeatStart(intSeatStart);
				daoRoom.setSeatEnd(intSeatEnd);
				daoRoom.setOperator(ShiroUtils.getUserId().toString());
				daoRoom.setStatus("a");
				int save = roomArrangeRegionService.save(daoRoom);

				strRoomArrangeid = daoRoom.getId()+"";
				//插入座次信息
				for (int i = intSeatStart; i <= intSeatEnd; i++)
				{
					SeatArrangeDO daoSeat = new SeatArrangeDO();
					daoSeat.setRoomArrangeid(Long.valueOf(strRoomArrangeid));
					daoSeat.setStudentCourseid(list3.get(intStudentCourseCount).getId());
					daoSeat.setExamCourseid(Long.valueOf(params.get("exam_courseid").toString()));
					daoSeat.setSeq(i) ;
					daoSeat.setOperator(ShiroUtils.getUserId().toString());
					seatArrangeRegionService.save(daoSeat);
					StudentCourseDO  studentCourseDO=new StudentCourseDO();
					studentCourseDO.setArrangeStatus("b");
					studentCourseDO.setId(list3.get(intStudentCourseCount).getId());
					studentCourseRegionService.update(studentCourseDO);

					//循环考生
					intStudentCourseCount++;
				}
			}
		}
		outMsg = "安排一门课程到一个考场成功！！";
	}
	catch (Exception ex)
	{

		outMsg = "安排一门课程到一个考场失败！\\n错误原因：" + ex.getMessage();
	}
	return outMsg;
}
	//删除考场安排</br>
	//输入值：考区编号,考试时间编号,操作员,ref 返回值,ref 返回消息
	@RequestMapping( "/removeRoomArrange")
	@ResponseBody
	@RequiresPermissions("region:roomArrangeRegion:remove")
	public R removeRoomArrange( @RequestParam Map<String, Object> params){
		params.put("regionid",ShiroUtils.getUser().getWorkerid());
		if(roomArrangeRegionService.removeRoomArrange(params)>0){
			return R.ok();
		}
		return R.error();
	}
	//考场编排确认</br>
	//输入值：考区编号,考试任务编号,操作员,ref 返回值,ref 返回消息
	@RequestMapping( "/roomArrangeConfirm")
	@ResponseBody
	public R roomArrangeConfirm( @RequestParam Map<String, Object> params){
		params.put("regionid",ShiroUtils.getUser().getWorkerid());
		if(roomArrangeRegionService.updateRoomArrangeConfirm(params)>0){
			return R.ok();
		}
		return R.error();
	}
//按照考点编号查找本次该考点中所有考场的课程安排信息(</br>
//输入值：考点编号,ref 返回值,ref 返回消息
// 返回数据集：编号,考场编号,课程代码,课程名称,座位数,已安排人数")]

    @GetMapping( "/roomArrangeCourses")
    public String site(@RequestParam Map<String, Object> params){
        //查询列表数据

        return "region/region/roomArrangeRegion/roomArrangeCourses";
    }
@GetMapping( "/getExamCoursesByExamSiteid")
@ResponseBody
public List<ExamCourseDO> getExamCoursesByExamSiteid(@RequestParam Map<String, Object> params){
	//查询列表数据
	//Query query = new Query(params);
	List<ExamCourseDO>  list=roomArrangeRegionService.ExamArrange_GetExamCoursesByExamSiteid(params);
	for (ExamCourseDO item : list) {
		item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
	}
	return list;
}

	//按照考区代码和考试时间查找本次该考点中所有考场的课程安排信息(考场编排调整)</br>
	//输入值：考区点好,考试时间,慢考场标志,ref 返回值,ref 返回消息</br>
	// 返回数据集：编号,考点代码,考点名称,考场编号,课程代码,课程名称,座位数,已安排人数
	@GetMapping( "/getExamCoursesByExamSiteid_Adjust")
	@ResponseBody
	public List<ExamCourseDO> getExamCoursesByExamSiteid_Adjust(@RequestParam Map<String, Object> params){
		//查询列表数据
		//Query query = new Query(params);
		List<ExamCourseDO>  list=roomArrangeRegionService.ExamArrange_GetExamCoursesByExamSiteid_Adjust(params);
		for (ExamCourseDO item : list) {
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
		}
		return list;
	}
	//按照考区代码和考试时间查找本次该考点中所有考场的课程安排信息(考场编排调整)</br>
	//输入值：考区点好,考试时间,慢考场标志,ref 返回值,ref 返回消息</br>
	//返回数据集：编号,考点代码,考点名称,考场编号,课程代码,课程名称,座位数,已安排人数
	@GetMapping( "/getExamCoursesByExamSiteid_Room_Adjust")
	@ResponseBody
	public List<ExamCourseDO> getExamCoursesByExamSiteid_Room_Adjust(@RequestParam Map<String, Object> params){
		//查询列表数据
		//Query query = new Query(params);
		params.put("regionid",ShiroUtils.getUser().getWorkerid());
		List<ExamCourseDO>  list=roomArrangeRegionService.ExamArrange_GetExamCoursesByExamSiteid_Room_Adjust(params);
		for (ExamCourseDO item : list) {
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
		}
		return list;
	}
	//按照考区代码和考试时间查找本次该考点中所有考场的课程安排信息(考场编排调整)</br>
	// 输入值：考区点好,考试时间,慢考场标志,ref 返回值,ref 返回消息</br>
	//  返回数据集：编号,考点代码,考点名称,考场编号,课程代码,课程名称,座位数,已安排人数"
	@GetMapping( "/getChildRegionsByRegionid")
	@ResponseBody
	public List<RegionDO> getChildRegionsByRegionid(@RequestParam Map<String, Object> params){
		//查询列表数据
		//Query query = new Query(params);
		params.put("regionid",ShiroUtils.getUser().getWorkerid());
		List<RegionDO>  list=roomArrangeRegionService.ExamArrange_GetChildRegionsByRegionid(params);

		return list;
	}
//考场编排调整</br>
// 输入值：转出考场编排编号,转入考场编排编号,操作员,ref 返回值,ref 返回消息"
@RequestMapping( "/saveRoomArrangeShift")
@ResponseBody
public R saveRoomArrangeShift( @RequestParam Map<String, Object> params){
	params.put("operator",ShiroUtils.getUserId().toString());

	return roomArrangeRegionService.saveRoomArrangeShift(params);
}
}
