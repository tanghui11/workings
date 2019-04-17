package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.domain.StudentChangeDO;

import java.util.List;
import java.util.Map;

/**
 * 考生信息变更表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
public interface StudentChangeService {
	
	StudentChangeDO get(Long id);
	
	List<StudentChangeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentChangeDO studentChange);
	
	int update(StudentChangeDO studentChange);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    int getSpecialityid(Long id);
}
