package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.StudentCourseDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 考生报考表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
public interface StudentCourseRepaireService {
	
	StudentCourseDO get(Long id);
	
	List<StudentCourseDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentCourseDO studentCourse);
	
	int update(StudentCourseDO studentCourse);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<StudentCourseDO> listBkCourse(Query query);

	int countBkCourse(Query query);

	List<StudentCourseDO> listBkStudent(Query query);

	int countBkStudent(Query query);

    String batchImport(String fileName, MultipartFile file);

	void saveBatch(List<StudentCourseDO> userKnowledgeBaseList);
}
