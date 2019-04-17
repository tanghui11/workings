package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.domain.StudentSpecialityPreDO;

import java.util.List;
import java.util.Map;

/**
 * 考生报考专业信息_预报名
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:26
 */
public interface StudentSpecialityPreService {
	
	StudentSpecialityPreDO get(Long id);
	
	List<StudentSpecialityPreDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentSpecialityPreDO studentSpecialityPre);
	
	int update(StudentSpecialityPreDO studentSpecialityPre);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
