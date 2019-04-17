package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.StudentInDO;

import java.util.List;
import java.util.Map;

/**
 * 考生转入考籍
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
public interface StudentInService {
	
	StudentInDO get(String id);
	
	List<StudentInDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentInDO studentIn);
	
	int update(StudentInDO studentIn);
	
	int remove(Long id);


	int batchRemove(Long[] ids);

	//取消调档（考生考籍转入）
	int cancelStatus(Long id);

	//院校端 调档（考籍转入调档）
	int migration(Long id);

    int cancelAuditStatus(Long id);

	int auditStatus(Long id);

    List<StudentInDO> migrationList(Query query);

	int migrationCount(Query query);


}