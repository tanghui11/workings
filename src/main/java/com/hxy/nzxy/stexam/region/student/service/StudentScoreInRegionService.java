package com.hxy.nzxy.stexam.region.student.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.StudentScoreInDO;

import java.util.List;
import java.util.Map;

/**
 * 转入成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
public interface StudentScoreInRegionService {
	
	StudentScoreInDO get(Long id);
	
	List<StudentScoreInDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentScoreInDO studentScoreInRegion);
	
	int update(StudentScoreInDO studentScoreInRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<StudentScoreInDO> migrationList(Map<String, Object> map);

	int migrationCount(Map<String, Object> map);
}
