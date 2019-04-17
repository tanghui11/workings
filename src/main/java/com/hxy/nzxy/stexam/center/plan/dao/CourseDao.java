package com.hxy.nzxy.stexam.center.plan.dao;

import com.hxy.nzxy.stexam.domain.CourseDO;
import com.hxy.nzxy.stexam.domain.SpecialityDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 课程管理
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
@Mapper
public interface CourseDao {

	CourseDO get(String id);
	
	List<CourseDO> list(Map<String, Object> map);

	List<CourseDO> listCourses(Map<String, Object> map);

	List<CourseDO> listxj(Map<String, Object> map);

//	List<CourseDO> listsj(Map<String, Object> map);
	
	int count(Map<String, Object> map);

	int countCourses(Map<String, Object> map);

	int countxj(Map<String, Object> map);
	
	int save(CourseDO course);
	
	int update(CourseDO course);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	List<CourseDO> listCourse(List<String> list);

	List<CourseDO> listCZ(List<CourseDO> userKnowledgeBaseList);
}
