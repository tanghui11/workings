package com.hxy.nzxy.stexam.region.student.dao;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.ExamCourseDO;
import com.hxy.nzxy.stexam.domain.StudentCourseDO;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.domain.StudentSpecialityDO;
import com.hxy.nzxy.stexam.domain.TaskDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考生报考表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
@Mapper
public interface StudentCourseRegionDao {

	StudentCourseDO get(Long id);
	
	List<StudentCourseDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentCourseDO studentCourseRegion);
	
	int update(StudentCourseDO studentCourseRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    int listCourseCount(Query query);

	List<ExamCourseDO> listCourse(Query query);

	int countStudent(Query query);

	List<StudentSpecialityDO> listStudent(Query query);

	List<TaskDO> listTask(Map<String,Object> params);

    List<StudentCourseDO> selectstudentCourse(Map map);

	List<StudentCourseDO> selectstudentCjCourse(Map map1);

	void batchSave(List<StudentCourseDO> list);
	List<ExamCourseDO> listCourseNew(Query query);
	int listCourseCountNew(Query query);
    int selectSameExamTime(Map map);
}
