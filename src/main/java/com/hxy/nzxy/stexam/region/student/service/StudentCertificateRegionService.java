package com.hxy.nzxy.stexam.region.student.service;

import com.hxy.nzxy.stexam.domain.StudentCertificateDO;

import java.util.List;
import java.util.Map;

/**
 * 毕业证书管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
public interface StudentCertificateRegionService {
	
	StudentCertificateDO get(Long id);
	
	List<StudentCertificateDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentCertificateDO studentCertificateRegion);
	
	int update(StudentCertificateDO studentCertificateRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
