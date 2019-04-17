package com.hxy.nzxy.stexam.center.exam.dao;

import com.hxy.nzxy.stexam.domain.SecretOfficeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 保密室维护
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 15:58:19
 */
@Mapper
public interface SecretOfficeDao {

	SecretOfficeDO get(Long id);
	
	List<SecretOfficeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SecretOfficeDO secretOffice);
	
	int update(SecretOfficeDO secretOffice);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    void batchAudit(Map map);

	void batchAuditNo(Map map);
}
