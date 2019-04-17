package com.hxy.nzxy.stexam.school.student.dao;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.ExamCourseDO;
import com.hxy.nzxy.stexam.domain.StudentCourseDO;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.domain.TaskDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考生报考表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
@Mapper
public interface StudentCourseStudentDao {

	StudentCourseDO get(Long id);
	
	List<StudentCourseDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentCourseDO studentCourseStudent);
	
	int update(StudentCourseDO studentCourseStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<TaskDO> listTask(Map<String,Object> params);

    List<ExamCourseDO> listCourse(Query query);

	int listCourseCount(Query query);

    List<StudentCourseDO> selectstudentCourse(Map<String,Object> map);

    void batchSave(List<StudentCourseDO> list);

	List<StudentCourseDO> selectstudentCjCourse(Map map1);

    void saveBatch(List<StudentCourseDO> userKnowledgeBaseList);

	List<StudentCourseDO> listCZ(List<StudentCourseDO> userKnowledgeBaseList);

    List<StudentCourseDO> selectstudentSameTime(Map map);
}
