package com.hxy.nzxy.stexam.school.school.service;

import com.hxy.nzxy.stexam.domain.CollegeDO;

import java.util.List;
import java.util.Map;

/**
 * 学院管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 20:36:40
 */
public interface CollegeSchoolService {
	
	CollegeDO get(String id);
	
	List<CollegeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CollegeDO collegeSchool);
	
	int update(CollegeDO collegeSchool);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

    List<CollegeDO> listCollege(Map<String, Object> query);
}
