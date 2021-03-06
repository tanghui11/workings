package com.hxy.nzxy.stexam.region.student.service;

import com.hxy.nzxy.stexam.domain.StudentRegDO;

import java.util.List;
import java.util.Map;

/**
 * 考生注册
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
public interface StudentRegRegionService {
	
	StudentRegDO get(Long id);
	
	List<StudentRegDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentRegDO studentRegRegion);
	
	int update(StudentRegDO studentRegRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
