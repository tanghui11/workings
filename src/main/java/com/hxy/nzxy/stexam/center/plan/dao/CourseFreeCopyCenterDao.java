package com.hxy.nzxy.stexam.center.plan.dao;

import com.hxy.nzxy.stexam.domain.CourseFreeCopyDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 课程免考规则copy
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-16 10:13:51
 */
@Mapper
public interface CourseFreeCopyCenterDao {

	CourseFreeCopyDO get(String id);
	
	List<CourseFreeCopyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CourseFreeCopyDO courseFreeCopyCenter);
	
	int update(CourseFreeCopyDO courseFreeCopyCenter);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
