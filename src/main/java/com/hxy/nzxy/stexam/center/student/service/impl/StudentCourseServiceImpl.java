package com.hxy.nzxy.stexam.center.student.service.impl;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.ExamRoomDO;
import com.hxy.nzxy.stexam.domain.SeatArrangeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.StudentCourseDao;
import com.hxy.nzxy.stexam.domain.StudentCourseDO;
import com.hxy.nzxy.stexam.center.student.service.StudentCourseService;



@Service
public class StudentCourseServiceImpl implements StudentCourseService {
	@Autowired
	private StudentCourseDao studentCourseDao;
	
	@Override
	public StudentCourseDO get(Long id){
		return studentCourseDao.get(id);
	}
	
	@Override
	public List<StudentCourseDO> list(Map<String, Object> map){
		return studentCourseDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentCourseDao.count(map);
	}
	
	@Override
	public int save(StudentCourseDO studentCourse){
		return studentCourseDao.save(studentCourse);
	}
	
	@Override
	public int update(StudentCourseDO studentCourse){
		return studentCourseDao.update(studentCourse);
	}
	
	@Override
	public int remove(Long id){
		return studentCourseDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentCourseDao.batchRemove(ids);
	}

	@Override
	public List<Map<String, Object>> listRepairetLayOut(Query query) {
		return studentCourseDao.listRepairetLayOut(query);
	}

	@Override
	public int countRepairetLayOut(Query query) {
		return studentCourseDao.countRepairetLayOut(query);
	}

	@Override
	public List<Map<String, Object>> listSeat(Map<String, Object> params) {
		return studentCourseDao.listSeat(params);
	}

	@Override
	public int saveSeat(Map<String, Object> params,StudentCourseDO studentCourse) {
        Map<String, Object> map1 = studentCourseDao.selectSeq(params);
         studentCourseDao.save(studentCourse);
         if(map1!=null){
			 params.put("seq", Integer.valueOf(map1.get("seq").toString())+1);
		 }else{
			 params.put("seq", 1);
		 }
        params.put("student_courseid",studentCourse.getId());
        return studentCourseDao.saveSeat(params);
	}

	@Override
	public List<Map<String, Object>> selectSameTime(Map<String, Object> params) {
		return studentCourseDao.selectSameTime(params);
	}

	@Override
	public List<Map<String, Object>> selectLast(Map<String, Object> params) {
		return studentCourseDao.selectLast(params);
	}

	@Override
	public List<Map<String, Object>> selectSeatByid(Map<String, Object> params) {
		return studentCourseDao.selectSeatByid(params);
	}

	@Override
	public int removeSeat(Map<String, Object> params) {
        studentCourseDao.remove(Long.valueOf(params.get("student_courseid").toString()));
		return studentCourseDao.removeSeat(params);
	}
	@Override
	public int removeSeat1(Map<String, Object> params) {
		studentCourseDao.remove(Long.valueOf(params.get("id").toString()));
		return studentCourseDao.removeSeat(params);
	}
	@Override
	public ExamRoomDO selectRoom2(Map<String,Object> params) {
		return studentCourseDao.selectRoom2(params);
	}

	@Override
	public List<Map<String, Object>> selectRoom(Map<String, Object> params) {
		return studentCourseDao.selectRoom(params);
	}

	@Override
	public List<Map<String, Object>> selectSubmitSite(Map<String, Object> params) {
		return studentCourseDao.selectSubmitSite(params);
	}

    @Override
    public List<Map<String, Object>> selectExamRoomRoom(Map<String, Object> params) {
        return studentCourseDao.selectExamRoomRoom(params);
    }

    @Override
    public List<SeatArrangeDO> selectAllSeat(Map<String, Object> params) {
        return studentCourseDao.selectAllSeat(params);
    }

}
