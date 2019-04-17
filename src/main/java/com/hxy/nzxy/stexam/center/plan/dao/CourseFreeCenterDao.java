package com.hxy.nzxy.stexam.center.plan.dao;

import com.hxy.nzxy.stexam.domain.CourseFreeDO;
import com.hxy.nzxy.stexam.domain.CourseFreeCenterDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 课程免考规则
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-16 10:13:51
 */
@Mapper
public interface CourseFreeCenterDao {

	CourseFreeDO get(String id);
	
	List<CourseFreeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CourseFreeDO courseFreeCenter);

	int mkSave(CourseFreeCenterDO courseFreeCenter);
	
	int update(CourseFreeDO courseFreeCenter);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	//List<CourseFreeCenterDO> listMK(List<CourseFreeCenterDO> userKnowledgeBaseList);
}
