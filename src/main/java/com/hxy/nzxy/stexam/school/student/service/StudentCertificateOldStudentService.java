package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.domain.StudentCertificateOldDO;

import java.util.List;
import java.util.Map;

/**
 * 老毕业生数据
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
public interface StudentCertificateOldStudentService {
	
	StudentCertificateOldDO get(Long id);
	
	List<StudentCertificateOldDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentCertificateOldDO studentCertificateOldStudent);
	
	int update(StudentCertificateOldDO studentCertificateOldStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
