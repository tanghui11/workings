package com.hxy.nzxy.stexam.center.school.dao;

import com.hxy.nzxy.stexam.domain.CollegeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 学院管理
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:26
 */
@Mapper
public interface CollegeDao {

	CollegeDO get(String id);
	
	List<CollegeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CollegeDO college);
	
	int update(CollegeDO college);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

    List<CollegeDO> seachCollege(Map<String,Object> params);
}
