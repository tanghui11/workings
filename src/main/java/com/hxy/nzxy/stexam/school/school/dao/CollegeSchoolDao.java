package com.hxy.nzxy.stexam.school.school.dao;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.CollegeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 学院管理
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 20:36:40
 */
@Mapper
public interface CollegeSchoolDao {

	CollegeDO get(String id);
	
	List<CollegeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CollegeDO collegeSchool);
	
	int update(CollegeDO collegeSchool);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

    List<CollegeDO> listCollege(Map<String, Object> map);
}
