package com.hxy.nzxy.stexam.region.region.service.impl;

import com.hxy.nzxy.stexam.common.utils.R;
import com.hxy.nzxy.stexam.domain.ExamCourseDO;
import com.hxy.nzxy.stexam.domain.ExamRoomDO;
import com.hxy.nzxy.stexam.domain.RegionDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.region.dao.RoomArrangeRegionDao;
import com.hxy.nzxy.stexam.domain.RoomArrangeDO;
import com.hxy.nzxy.stexam.region.region.service.RoomArrangeRegionService;



@Service
public class RoomArrangeRegionServiceImpl implements RoomArrangeRegionService {
	@Autowired
	private RoomArrangeRegionDao roomArrangeRegionDao;
	
	@Override
	public RoomArrangeDO get(Long id){
		return roomArrangeRegionDao.get(id);
	}
	
	@Override
	public List<RoomArrangeDO> list(Map<String, Object> map){
		return roomArrangeRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return roomArrangeRegionDao.count(map);
	}
	
	@Override
	public int save(RoomArrangeDO roomArrangeRegion){
		return roomArrangeRegionDao.save(roomArrangeRegion);
	}
	
	@Override
	public int update(RoomArrangeDO roomArrangeRegion){
		return roomArrangeRegionDao.update(roomArrangeRegion);
	}
	
	@Override
	public int remove(Long id){
		return roomArrangeRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return roomArrangeRegionDao.batchRemove(ids);
	}

	@Override
	public List<ExamCourseDO> ExamArrange_GetStudentCourses(Map<String, Object> params) {
		return roomArrangeRegionDao.ExamArrange_GetStudentCourses(params);
	}

	@Override
	public List<ExamRoomDO> ExamArrange_GetExamRoomsByRegionid_ExamTaskid(Map<String, Object> params) {
		return roomArrangeRegionDao.ExamArrange_GetExamRoomsByRegionid_ExamTaskid(params);
	}

	@Override
	public List<ExamCourseDO> ExamArrange_GetStudentExamCourses1(Map<String, Object> params) {
		return roomArrangeRegionDao.ExamArrange_GetStudentExamCourses1(params);
	}

	@Override
	public List<ExamRoomDO> ExamArrange_GetExamRooms1(Map<String, Object> params) {
		return roomArrangeRegionDao.ExamArrange_GetExamRooms1(params);
	}

	@Override
	public List<ExamCourseDO> ExamArrange_GetStudentCourses1(Map<String, Object> params) {
		return roomArrangeRegionDao.ExamArrange_GetStudentCourses1(params);
	}

	@Override
	public List<ExamCourseDO> ExamArrange_GetStudentExamCourses2(Map<String, Object> params) {
		return roomArrangeRegionDao.ExamArrange_GetStudentExamCourses2(params);
	}

	@Override
	public List<ExamRoomDO> ExamArrange_GetExamRooms2(Map<String, Object> params) {
		return roomArrangeRegionDao.ExamArrange_GetExamRooms2(params);
	}

	@Override
	public List<ExamCourseDO> ExamArrange_GetStudentCourses2(Map<String, Object> params) {
		return roomArrangeRegionDao.ExamArrange_GetStudentCourses2(params) ;
	}

	@Override
	public List<ExamCourseDO> ExamArrange_GetStudentExamCourses3(Map<String, Object> params) {
		return roomArrangeRegionDao.ExamArrange_GetStudentExamCourses3(params);
	}

	@Override
	public List<ExamRoomDO> ExamArrange_GetExamRooms3(Map<String, Object> params) {
		return roomArrangeRegionDao.ExamArrange_GetExamRooms3(params);
	}

	@Override
	public List<ExamCourseDO> ExamArrange_GetStudentCourses3(Map<String, Object> params) {
		return roomArrangeRegionDao.ExamArrange_GetStudentCourses3(params);
	}

	@Override
	public List<ExamCourseDO> ExamArrange_GetExamRoomsCourseCount(Map<String, Object> params) {
		return roomArrangeRegionDao.ExamArrange_GetExamRoomsCourseCount(params);
	}

	@Override
	public List<ExamCourseDO> ExamArrange_GetStudentExamCourses4(Map<String, Object> params) {
		return roomArrangeRegionDao.ExamArrange_GetStudentExamCourses4(params);
	}

	@Override
	public List<ExamRoomDO> ExamArrange_GetExamRooms4(Map<String, Object> params) {
		return roomArrangeRegionDao.ExamArrange_GetExamRooms4(params);
	}

	@Override
	public List<ExamCourseDO> ExamArrange_GetStudentCourses4(Map<String, Object> params) {
		return roomArrangeRegionDao.ExamArrange_GetStudentCourses4(params);
	}

	@Override
	public List<ExamCourseDO> ExamArrange_GetStudentExamCourses5(Map<String, Object> params) {
		return roomArrangeRegionDao.ExamArrange_GetStudentExamCourses5(params);
	}

	@Override
	public List<ExamRoomDO> ExamArrange_GetExamRooms5(Map<String, Object> params) {
		return roomArrangeRegionDao.ExamArrange_GetExamRooms5(params);
	}

	@Override
	public List<ExamCourseDO> ExamArrange_GetStudentCourses5(Map<String, Object> params) {
		return roomArrangeRegionDao.ExamArrange_GetStudentCourses5(params);
	}

	@Override
	public int removeRoomArrange(Map<String, Object> params) {
		return roomArrangeRegionDao.removeRoomArrange(params);
	}

	@Override
	public int updateRoomArrangeConfirm(Map<String, Object> params) {
		return roomArrangeRegionDao.updateRoomArrangeConfirm(params);
	}

	@Override
	public List<ExamCourseDO> ExamArrange_GetExamCoursesByExamSiteid_Adjust(Map<String, Object> params) {
		return roomArrangeRegionDao.ExamArrange_GetExamCoursesByExamSiteid_Adjust(params);
	}

	@Override
	public List<ExamCourseDO> ExamArrange_GetExamCoursesByExamSiteid_Room_Adjust(Map<String, Object> params) {
		return roomArrangeRegionDao.ExamArrange_GetExamCoursesByExamSiteid_Room_Adjust(params);
	}

	@Override
	public List<RegionDO> ExamArrange_GetChildRegionsByRegionid(Map<String, Object> params) {
		return roomArrangeRegionDao.ExamArrange_GetChildRegionsByRegionid(params);
	}

	@Override
	public List<ExamCourseDO> ExamArrange_GetExamCoursesByExamSiteid(Map<String, Object> params) {
		return roomArrangeRegionDao.ExamArrange_GetExamCoursesByExamSiteid(params);
	}

	@Override
	public R saveRoomArrangeShift(Map<String,Object> params) {
		//--验证转出考场
		List<Map<String, Object>> maps1 = roomArrangeRegionDao.saveRoomArrangeShift1(params);
		if(maps1.get(0).get("exam_room_outid")==null){
		return R.error("没有找到要转转出的考场编排信息");
		}
		params.put("exam_room_outid",maps1.get(0).get("exam_room_outid"));
		params.put("out_num",maps1.get(0).get("out_num"));
		params.put("exam_timeid",maps1.get(0).get("exam_timeid"));
		params.put("regionid_outid",maps1.get(0).get("regionid_outid"));
		params.put("room_no_out",maps1.get(0).get("room_no_out"));
		params.put("exam_site_submitid",maps1.get(0).get("exam_site_submitid"));
		//--验证转出考场编排信息
		List<Map<String, Object>> maps2 = roomArrangeRegionDao.saveRoomArrangeShift2(params);
		if(maps2.size()>0){
			return R.error("转出考场的编排已经确认");
		}
		//--验证转入考场
		List<Map<String, Object>> maps3 = roomArrangeRegionDao.saveRoomArrangeShift3(params);
		if(maps3.get(0).get("exam_room_inid")==null){
			return R.error("没有找到要转转入的考场编排信息");
		}
		params.put("exam_room_inid",maps3.get(0).get("exam_room_inid"));
		params.put("seat_num",maps3.get(0).get("seat_num"));
		params.put("regionid_inid",maps3.get(0).get("regionid_inid"));
		//--验证转入考场编排信
		List<Map<String, Object>> maps4 = roomArrangeRegionDao.saveRoomArrangeShift4(params);
		if(maps4.size()>0){
			return R.error("转入考场的编排已经确认");
		}
		//--转入考场剩余座位数
		List<Map<String, Object>> maps5= roomArrangeRegionDao.saveRoomArrangeShift5(params);
		if(maps5.size()>0){
			params.put("in_num",maps5.get(0).get("in_num"));
			if(Integer.valueOf(maps1.get(0).get("out_num").toString())>( Integer.valueOf(maps3.get(0).get("seat_num").toString())-Integer.valueOf(maps5.get(0).get("in_num").toString()))){
				return R.error("转入考场剩余"+Integer.valueOf(maps5.get(0).get("in_num").toString())+"个座位，不能容纳"+Integer.valueOf(maps1.get(0).get("out_num").toString())+"个转出考生");
			}

		}
		//--更新考场编排信息：将转出考场的考场编号更新为转入考场的考场编号
		roomArrangeRegionDao.saveRoomArrangeShift6(params);
		// --转出考场重新编排座次
		List<Map<String, Object>> maps7 = roomArrangeRegionDao.saveRoomArrangeShift7(params);
		for (int i = 0; i <maps7.size() ; i++) {
			params.put("room_arrange_outid",maps7.get(i).get("room_arrange_outid"));
			params.put("exam_courseid",maps7.get(i).get("exam_courseid"));
			//开始座位号
			List<Map<String, Object>> maps8 = roomArrangeRegionDao.saveRoomArrangeShift8(params);
			for (int j = 0; i <maps8.size() ; i++) {
				params.put("seat_arrange_outid",maps8.get(j).get("seat_arrange_outid"));
				//更新座位号
				roomArrangeRegionDao.saveRoomArrangeShift9(params);
			}
			//--更新考场安排表中的座位开始号和结束号
			roomArrangeRegionDao.saveRoomArrangeShift10(params);
		}
		//--转入考场重新编排座次
		List<Map<String, Object>> maps11 = roomArrangeRegionDao.saveRoomArrangeShift11(params);
		for (int i = 0; i <maps11.size() ; i++) {
			params.put("room_arrange_inid",maps11.get(i).get("room_arrange_inid"));
			params.put("exam_courseid",maps11.get(i).get("exam_courseid"));
			//开始座位号
			List<Map<String, Object>> maps12 = roomArrangeRegionDao.saveRoomArrangeShift12(params);
			for (int j = 0; i <maps12.size() ; i++) {
				params.put("seat_arrange_inid",maps12.get(j).get("seat_arrange_inid"));
				//更新座位号
				roomArrangeRegionDao.saveRoomArrangeShift13(params);
			}
			//--更新考场安排表中的座位开始号和结束号
			roomArrangeRegionDao.saveRoomArrangeShift14(params);
		}
		//--如果转出考场已经没有其他课程，该考场号以后考场自动缩位
		List<Map<String, Object>> maps15 = roomArrangeRegionDao.saveRoomArrangeShift15(params);
		if(maps15.size()==0){
			roomArrangeRegionDao.saveRoomArrangeShift16(params);
		}
		return R.ok();
	}

	@Override
	public List<Map<String, Object>> ExamArrange_GetExamSites(Map<String, Object> params) {
		return roomArrangeRegionDao.ExamArrange_GetExamSites(params);
	}


}
