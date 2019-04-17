package com.hxy.nzxy.stexam.center.plan.dao;

import com.hxy.nzxy.stexam.domain.CourseCopyDO;
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
public interface CourseCopyDao {

	CourseCopyDO get(String id);
	
	List<CourseCopyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CourseCopyDO courseCopy);
	
	int update(CourseCopyDO courseCopy);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
