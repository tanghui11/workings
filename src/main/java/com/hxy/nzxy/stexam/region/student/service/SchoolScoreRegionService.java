package com.hxy.nzxy.stexam.region.student.service;

import com.hxy.nzxy.stexam.domain.SchoolScoreDO;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 平时成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
public interface SchoolScoreRegionService {
	
	SchoolScoreDO get(Long id);
	
	List<SchoolScoreDO> list(Map<String, Object> map) throws ParseException;

	List<SchoolScoreDO> listT(Map<String, Object> map) throws ParseException;
	
	int count(Map<String, Object> map);
	
	int save(SchoolScoreDO schoolScoreRegion);
	
	int update(SchoolScoreDO schoolScoreRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	int updateStatus(SchoolScoreDO schoolScoreRegionList);
}
