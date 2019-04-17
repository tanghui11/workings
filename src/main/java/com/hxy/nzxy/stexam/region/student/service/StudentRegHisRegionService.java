package com.hxy.nzxy.stexam.region.student.service;

import com.hxy.nzxy.stexam.domain.StudentRegHisDO;

import java.util.List;
import java.util.Map;

/**
 * 考生注册_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
public interface StudentRegHisRegionService {
	
	StudentRegHisDO get(Long id);
	
	List<StudentRegHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentRegHisDO studentRegHisRegion);
	
	int update(StudentRegHisDO studentRegHisRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
