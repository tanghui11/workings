package com.hxy.nzxy.stexam.region.student.dao;

import com.hxy.nzxy.stexam.domain.SchoolScoreDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 平时成绩
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
@Mapper
public interface SchoolScoreRegionDao {

	SchoolScoreDO get(Long id);
	
	List<SchoolScoreDO> list(Map<String,Object> map);

	List<SchoolScoreDO> listT(Map<String,Object> map);

	int count(Map<String,Object> map);
	
	int save(SchoolScoreDO schoolScoreRegion);
	
	int update(SchoolScoreDO schoolScoreRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	int updateStatus(SchoolScoreDO schoolScoreRegionList);
}
