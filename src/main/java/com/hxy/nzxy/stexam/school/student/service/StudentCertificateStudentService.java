package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.domain.StudentCertificateDO;

import java.util.List;
import java.util.Map;

/**
 * 毕业证书管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
public interface StudentCertificateStudentService {
	
	StudentCertificateDO get(Long id);
	
	List<StudentCertificateDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentCertificateDO studentCertificateStudent);
	
	int update(StudentCertificateDO studentCertificateStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
