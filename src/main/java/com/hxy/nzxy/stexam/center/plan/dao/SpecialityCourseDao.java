package com.hxy.nzxy.stexam.center.plan.dao;

import com.hxy.nzxy.stexam.domain.SpecialityCourseDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 专业课程
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:50
 */
@Mapper
public interface SpecialityCourseDao {

	SpecialityCourseDO get(Long id);
	
	List<SpecialityCourseDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SpecialityCourseDO specialityCourse);
	
	int update(SpecialityCourseDO specialityCourse);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    int batchSave(List<SpecialityCourseDO> list);

	List<SpecialityCourseDO> searchSpecialityIdList(Map<String,Object> map);

	static List<SpecialityCourseDO> listCZ(List<SpecialityCourseDO> userKnowledgeBaseList) {
		return userKnowledgeBaseList;
	}
	static void saveBatch(List<SpecialityCourseDO> userKnowledgeBaseList) {}
}
