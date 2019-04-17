package com.hxy.nzxy.stexam.region.exam.dao;

import com.hxy.nzxy.stexam.domain.SecretOfficeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 保密室维护
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-11 10:32:23
 */
@Mapper
public interface SecretOfficeRegionDao {

	SecretOfficeDO get(Long id);
	
	List<SecretOfficeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SecretOfficeDO secretOfficeRegion);
	
	int update(SecretOfficeDO secretOfficeRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
