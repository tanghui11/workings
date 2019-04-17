package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.domain.StudentCertificateOldDO;

import java.util.List;
import java.util.Map;

/**
 * 老毕业生数据
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
public interface StudentCertificateOldService {
	
	StudentCertificateOldDO get(Long id);
	
	List<StudentCertificateOldDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentCertificateOldDO studentCertificateOld);
	
	int update(StudentCertificateOldDO studentCertificateOld);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
