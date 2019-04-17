package com.hxy.nzxy.stexam.school.school.service;

import com.hxy.nzxy.stexam.domain.SchoolDO;
import com.hxy.nzxy.stexam.system.domain.DeptWorkerDO;

import java.util.List;
import java.util.Map;

/**
 * 助学组织
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 20:36:41
 */
public interface SchoolSchoolService {
	
	SchoolDO get(Long id);
	
	List<SchoolDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SchoolDO schoolSchool);
	
	int update(SchoolDO schoolSchool);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	SchoolDO getDept(String workerid);
}
