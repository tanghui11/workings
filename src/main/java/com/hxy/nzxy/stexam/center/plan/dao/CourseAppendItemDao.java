package com.hxy.nzxy.stexam.center.plan.dao;

import com.hxy.nzxy.stexam.domain.CourseAppendItemDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 须加考专业
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
@Mapper
public interface CourseAppendItemDao {

	CourseAppendItemDO get(Long id);
	
	List<CourseAppendItemDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CourseAppendItemDO courseAppendItem);
	
	int update(CourseAppendItemDO courseAppendItem);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
