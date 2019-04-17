package com.hxy.nzxy.stexam.region.region.service;

import com.hxy.nzxy.stexam.common.utils.R;
import com.hxy.nzxy.stexam.domain.ExamCourseDO;
import com.hxy.nzxy.stexam.domain.ExamRoomDO;
import com.hxy.nzxy.stexam.domain.RegionDO;
import com.hxy.nzxy.stexam.domain.RoomArrangeDO;

import java.util.List;
import java.util.Map;

/**
 * 考场编排
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
public interface RoomArrangeRegionService {
	
	RoomArrangeDO get(Long id);
	
	List<RoomArrangeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RoomArrangeDO roomArrangeRegion);
	
	int update(RoomArrangeDO roomArrangeRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<ExamCourseDO> ExamArrange_GetStudentCourses(Map<String,Object> params);

	List<ExamRoomDO> ExamArrange_GetExamRoomsByRegionid_ExamTaskid(Map<String,Object> params);

    List<ExamCourseDO> ExamArrange_GetStudentExamCourses1(Map<String,Object> params);

	List<ExamRoomDO> ExamArrange_GetExamRooms1(Map<String,Object> params);

	List<ExamCourseDO> ExamArrange_GetStudentCourses1(Map<String,Object> params);

	List<ExamCourseDO> ExamArrange_GetStudentExamCourses2(Map<String,Object> params);

	List<ExamRoomDO> ExamArrange_GetExamRooms2(Map<String,Object> params);

	List<ExamCourseDO> ExamArrange_GetStudentCourses2(Map<String,Object> params);

	List<ExamCourseDO> ExamArrange_GetStudentExamCourses3(Map<String,Object> params);

	List<ExamRoomDO> ExamArrange_GetExamRooms3(Map<String,Object> params);

	List<ExamCourseDO> ExamArrange_GetStudentCourses3(Map<String,Object> params);

	List<ExamCourseDO> ExamArrange_GetExamRoomsCourseCount(Map<String,Object> params);

	List<ExamCourseDO> ExamArrange_GetStudentExamCourses4(Map<String,Object> params);

	List<ExamRoomDO> ExamArrange_GetExamRooms4(Map<String,Object> params);

	List<ExamCourseDO> ExamArrange_GetStudentCourses4(Map<String,Object> params);

	List<ExamCourseDO> ExamArrange_GetStudentExamCourses5(Map<String,Object> params);

	List<ExamRoomDO> ExamArrange_GetExamRooms5(Map<String,Object> params);

	List<ExamCourseDO> ExamArrange_GetStudentCourses5(Map<String,Object> params);

	int removeRoomArrange(Map<String,Object> params);

	int updateRoomArrangeConfirm(Map<String,Object> params);

	List<ExamCourseDO> ExamArrange_GetExamCoursesByExamSiteid_Adjust(Map<String,Object> params);

	List<ExamCourseDO> ExamArrange_GetExamCoursesByExamSiteid_Room_Adjust(Map<String,Object> params);

	List<RegionDO> ExamArrange_GetChildRegionsByRegionid(Map<String,Object> params);

	List<ExamCourseDO> ExamArrange_GetExamCoursesByExamSiteid(Map<String,Object> params);

	R saveRoomArrangeShift(Map<String,Object> params);

	List<Map<String,Object>> ExamArrange_GetExamSites(Map<String,Object> params);
}
