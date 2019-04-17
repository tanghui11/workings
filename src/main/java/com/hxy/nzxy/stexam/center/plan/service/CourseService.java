package com.hxy.nzxy.stexam.center.plan.service;

import com.hxy.nzxy.stexam.domain.CourseDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 课程管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
public interface CourseService {
	
	CourseDO get(String id);
	
	List<CourseDO> list(Map<String, Object> map);

	List<CourseDO> listCourses(Map<String, Object> map);

	List<CourseDO> listxj(Map<String, Object> map);

//	List<CourseDO> listsj(Map<String, Object> map);

	int countxj(Map<String, Object> map);

	int count(Map<String, Object> map);

	int countCourses(Map<String, Object> map);

	int save(CourseDO course);
	
	int update(CourseDO course);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	String batchImport(String fileName, MultipartFile file);
}
