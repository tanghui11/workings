package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.domain.ScoreDO;
import com.hxy.nzxy.stexam.domain.StudentSpecialityDO;

import java.util.List;
import java.util.Map;

/**
 * 考生报考专业信息表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:45
 */
public interface StudentSpecialityStudentService {
	
	StudentSpecialityDO get(Long id);
	
	List<StudentSpecialityDO> list(Map<String, Object> map);

	List<ScoreDO> applyExcelScore(String studentid, String specialityRecordid);

	int count(Map<String, Object> map);
	
	int save(StudentSpecialityDO studentSpecialityStudent);
	
	int update(StudentSpecialityDO studentSpecialityStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    StudentSpecialityDO getByStudentId(String id);
}
