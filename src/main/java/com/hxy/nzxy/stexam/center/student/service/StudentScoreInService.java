package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.StudentScoreInDO;

import java.util.List;
import java.util.Map;

/**
 * 转入成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
public interface StudentScoreInService {
	
	StudentScoreInDO get(Long id);
	
	List<StudentScoreInDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentScoreInDO studentScoreIn);
	
	int update(StudentScoreInDO studentScoreIn);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<StudentScoreInDO> migrationList(Query query);

	int migrationCount(Query query);
}
