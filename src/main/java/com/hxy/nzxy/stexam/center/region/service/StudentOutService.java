package com.hxy.nzxy.stexam.center.region.service;

import com.hxy.nzxy.stexam.domain.StudentOutDO;
import com.hxy.nzxy.stexam.domain.StudentOutSpecialityDO;

import java.util.List;
import java.util.Map;

/**
 * 考绩转出
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:19
 */
public interface StudentOutService {
	
	StudentOutDO get(Long id);
	
	List<StudentOutDO> list(Map<String, Object> map);

	List<StudentOutSpecialityDO> studentOutSpecialityList(Map<String, Object> map);
	
	int count(Map<String, Object> map);

	int studentOutSpecialityCount (Map<String, Object> map);
	
	int save(StudentOutDO studentOut);
	
	int update(StudentOutDO studentOut);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	int out(Long id);
}
