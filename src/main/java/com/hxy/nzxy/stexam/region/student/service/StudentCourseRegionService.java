package com.hxy.nzxy.stexam.region.student.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.ExamCourseDO;
import com.hxy.nzxy.stexam.domain.StudentCourseDO;
import com.hxy.nzxy.stexam.domain.StudentSpecialityDO;
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
 * @date 2018-08-22 20:06:14
 */
public interface StudentCourseRegionService {
	
	StudentCourseDO get(Long id);
	
	List<StudentCourseDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentCourseDO studentCourseRegion);
	
	int update(StudentCourseDO studentCourseRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<StudentSpecialityDO> listStudent(Query query);

	int countStudent(Query query);

	List<ExamCourseDO> listCourse(Query query);

	int listCourseCount(Query query);

	List<TaskDO> listTask(Map<String,Object> params);

	int saveCourse(String[] examCourseid, String childRegionid,String regionid,String studentid);

    String batchImport(String fileName, MultipartFile file, HttpServletRequest request);
	List<ExamCourseDO> listCourseNew(Query query);

	int listCourseCountNew(Query query);

	int selectSameExamTime(String[] examCourseid, String studentid);
}
