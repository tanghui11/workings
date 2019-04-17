package com.hxy.nzxy.stexam.center.plan.dao;

import com.hxy.nzxy.stexam.domain.CourseCheckDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 课程复选
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
@Mapper
public interface CourseCheckDao {

	CourseCheckDO get(Long id);
	
	List<CourseCheckDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CourseCheckDO courseCheck);
	
	int update(CourseCheckDO courseCheck);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
