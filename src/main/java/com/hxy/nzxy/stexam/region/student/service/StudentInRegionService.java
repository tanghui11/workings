package com.hxy.nzxy.stexam.region.student.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.StudentInDO;

import java.util.List;
import java.util.Map;

/**
 * 考生转入考籍
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
public interface StudentInRegionService {
	
	StudentInDO get(Long id);
	
	List<StudentInDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentInDO studentInRegion);
	
	int update(StudentInDO studentInRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    void updateMigration(StudentInDO studentInRegion);

	List<StudentInDO> migrationList(Map<String, Object> map);

	int migrationCount(Map<String, Object> map);
}
