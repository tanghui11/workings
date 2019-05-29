package com.hxy.nzxy.stexam.region.student.service;

import com.hxy.nzxy.stexam.domain.StudentHisDO;

import java.util.List;
import java.util.Map;

/**
 * 考生基本信息表_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
public interface StudentHisRegionService {
	
	StudentHisDO get(String id);
	
	List<StudentHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentHisDO studentHisRegion);
	
	int update(StudentHisDO studentHisRegion);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}