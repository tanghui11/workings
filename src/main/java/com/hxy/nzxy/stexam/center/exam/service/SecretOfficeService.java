package com.hxy.nzxy.stexam.center.exam.service;

import com.hxy.nzxy.stexam.domain.SecretOfficeDO;

import java.util.List;
import java.util.Map;

/**
 * 保密室维护
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 15:58:19
 */
public interface SecretOfficeService {
	
	SecretOfficeDO get(Long id);
	
	List<SecretOfficeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SecretOfficeDO secretOffice);
	
	int update(SecretOfficeDO secretOffice);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    void batchAudit(Long[] ids);

	void batchAuditNo(Long[] ids);
}
