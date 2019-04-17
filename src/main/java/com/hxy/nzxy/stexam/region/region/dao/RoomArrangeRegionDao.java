package com.hxy.nzxy.stexam.region.region.dao;

import com.hxy.nzxy.stexam.domain.ExamCourseDO;
import com.hxy.nzxy.stexam.domain.ExamRoomDO;
import com.hxy.nzxy.stexam.domain.RegionDO;
import com.hxy.nzxy.stexam.domain.RoomArrangeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考场编排
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
@Mapper
public interface RoomArrangeRegionDao {

	RoomArrangeDO get(Long id);
	
	List<RoomArrangeDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
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

	List<ExamCourseDO> ExamArrange_GetStudentCourses4(Map<String,Object> params);

	List<ExamRoomDO> ExamArrange_GetExamRooms4(Map<String,Object> params);

	List<ExamCourseDO> ExamArrange_GetStudentExamCourses4(Map<String,Object> params);

	List<ExamCourseDO> ExamArrange_GetStudentExamCourses5(Map<String,Object> params);

	List<ExamRoomDO> ExamArrange_GetExamRooms5(Map<String,Object> params);

	List<ExamCourseDO> ExamArrange_GetStudentCourses5(Map<String,Object> params);

	int removeRoomArrange(Map<String,Object> params);

    int updateRoomArrangeConfirm(Map<String,Object> params);

	List<ExamCourseDO> ExamArrange_GetExamCoursesByExamSiteid_Adjust(Map<String,Object> params);

	List<ExamCourseDO> ExamArrange_GetExamCoursesByExamSiteid_Room_Adjust(Map<String,Object> params);

	List<RegionDO> ExamArrange_GetChildRegionsByRegionid(Map<String,Object> params);

	List<ExamCourseDO> ExamArrange_GetExamCoursesByExamSiteid(Map<String,Object> params);

	int saveRoomArrangeShift(Map<String,Object> params);
	List<Map<String,Object>> saveRoomArrangeShift1(Map<String,Object> params);
	List<Map<String,Object>> saveRoomArrangeShift2(Map<String,Object> params);
	List<Map<String,Object>> saveRoomArrangeShift3(Map<String,Object> params);
	List<Map<String,Object>> saveRoomArrangeShift4(Map<String,Object> params);
	List<Map<String,Object>> saveRoomArrangeShift5(Map<String,Object> params);
	int saveRoomArrangeShift6(Map<String,Object> params);
	List<Map<String,Object>> saveRoomArrangeShift7(Map<String,Object> params);
	List<Map<String,Object>> saveRoomArrangeShift8(Map<String,Object> params);
	int saveRoomArrangeShift9(Map<String,Object> params);
	int saveRoomArrangeShift10(Map<String,Object> params);
	List<Map<String,Object>> saveRoomArrangeShift11(Map<String,Object> params);
	List<Map<String,Object>> saveRoomArrangeShift12(Map<String,Object> params);
	int saveRoomArrangeShift13(Map<String,Object> params);
	int saveRoomArrangeShift14(Map<String,Object> params);
	List<Map<String,Object>> saveRoomArrangeShift15(Map<String,Object> params);
	int saveRoomArrangeShift16(Map<String,Object> params);


	List<Map<String,Object>> ExamArrange_GetExamSites(Map<String,Object> params);
}
