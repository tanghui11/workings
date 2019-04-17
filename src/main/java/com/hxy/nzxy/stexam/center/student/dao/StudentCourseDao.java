package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.ExamRoomDO;
import com.hxy.nzxy.stexam.domain.SeatArrangeDO;
import com.hxy.nzxy.stexam.domain.StudentCourseDO;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.domain.StudentCourseTempDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 考生报考表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
@Mapper
public interface StudentCourseDao {

	StudentCourseDO get(Long id);
	
	List<StudentCourseDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentCourseDO studentCourse);

	int tempSave(StudentCourseTempDO studentCourseTempDO);
	
	int update(StudentCourseDO studentCourse);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	int meger(@Param("oldStudentid") String oldStudentid, @Param("newStudentid") String newStudentid, @Param("courseid") String courseid);

	int reMeger(@Param("oldStudentid") String oldStudentid, @Param("newStudentid") String newStudentid, @Param("courseid") String courseid);

	int megerZkz(@Param("oldStudentid") String oldStudentid, @Param("newStudentid") String newStudentid);

	int reMegerZkz(@Param("oldStudentid") String oldStudentid, @Param("newStudentid") String newStudentid);
	List<Map<String, Object>> listRepairetLayOut(Query query);

	int countRepairetLayOut(Query query);

	List<Map<String,Object>> listSeat(Map<String,Object> params);

	int saveSeat(Map<String,Object> params);

	List<Map<String,Object>> selectSameTime(Map<String,Object> params);

	List<Map<String,Object>> selectLast(Map<String,Object> params);

	List<Map<String,Object>> selectSeatByid(Map<String,Object> params);

	int removeSeat(Map<String,Object> params);

    ExamRoomDO selectRoom2(Map<String,Object> params);

    List<Map<String,Object>> selectRoom(Map<String,Object> params);

	List<Map<String,Object>> selectSubmitSite(Map<String,Object> params);

	Map<String,Object> selectSeq(Map<String,Object> params);

	Map<String,Object> selectStudent_courseid(Map<String,Object> params);

    List<Map<String,Object>> selectExamRoomRoom(Map<String,Object> params);

    List<SeatArrangeDO> selectAllSeat(Map<String,Object> params);
}
