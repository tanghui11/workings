package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.ExamCourseDO;
import com.hxy.nzxy.stexam.domain.StudentCourseDO;
import com.hxy.nzxy.stexam.domain.TaskDO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 考生报考表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
public interface StudentCourseStudentService {
	
	StudentCourseDO get(Long id);
	
	List<StudentCourseDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentCourseDO studentCourseStudent);
	
	int update(StudentCourseDO studentCourseStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<TaskDO> listTask(Map<String,Object> params);

    List<ExamCourseDO> listCourse(Query query);

	int listCourseCount(Query query);


	List<StudentCourseDO>  saveCourse(String stuList, String courseList, String teachid, HttpServletRequest request);


	void saveBatch(List<StudentCourseDO> userKnowledgeBaseList);

	String batchImport(String fileName, MultipartFile file, HttpServletRequest request);
}
