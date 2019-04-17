package com.hxy.nzxy.stexam.center.plan.dao;

import com.hxy.nzxy.stexam.domain.CourseReplaceItemDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 顶替课程
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
@Mapper
public interface CourseReplaceItemDao {

	CourseReplaceItemDO get(Long id);
	
	List<CourseReplaceItemDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CourseReplaceItemDO courseReplaceItem);
	
	int update(CourseReplaceItemDO courseReplaceItem);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	CourseReplaceItemDO selectInDB(CourseReplaceItemDO courseReplaceItem);
}
