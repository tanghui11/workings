package com.hxy.nzxy.stexam.region.exam.dao;

import com.hxy.nzxy.stexam.domain.EducateLengthDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 学制定义
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-11 10:32:22
 */
@Mapper
public interface EducateLengthRegionDao {

	EducateLengthDO get(Long id);
	
	List<EducateLengthDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(EducateLengthDO educateLengthRegion);
	
	int update(EducateLengthDO educateLengthRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
