package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.domain.StudentHisDO;

import java.util.List;
import java.util.Map;

/**
 * 考生基本信息表_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
public interface StudentHisService {
	
	StudentHisDO get(String id);
	
	List<StudentHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentHisDO studentHis);
	
	int update(StudentHisDO studentHis);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
