package com.hxy.nzxy.stexam.center.plan.dao;

import com.hxy.nzxy.stexam.domain.CourseDO;
import com.hxy.nzxy.stexam.domain.OldCourseDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 老课程管理
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
@Mapper
public interface OldCourseDao {

	OldCourseDO get(String id);
	
	List<OldCourseDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OldCourseDO oldCourse);
	
	int update(OldCourseDO oldCourse);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

    List searchlist(Map<String,Object> params);

    List<OldCourseDO> listCZ(List<OldCourseDO> userKnowledgeBaseList);

	void saveBatch(List<OldCourseDO> userKnowledgeBaseList);
}
