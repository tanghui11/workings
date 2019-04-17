package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.domain.StudentRegDO;

import java.util.List;
import java.util.Map;

/**
 * 考生注册
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
public interface StudentRegService {
	
	StudentRegDO get(Long id);
	
	List<StudentRegDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentRegDO studentReg);
	
	int update(StudentRegDO studentReg);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    int updateAudit(Map<String,Object> params);

	int batchUpdateAudit(Long[] ids, String auditStatus);
}
