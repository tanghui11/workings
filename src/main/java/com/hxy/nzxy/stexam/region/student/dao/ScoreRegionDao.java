package com.hxy.nzxy.stexam.region.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考生成绩表_历史表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
@Mapper
public interface ScoreRegionDao {

	ScoreDO get(Long id);
	
	List<ScoreDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ScoreDO scoreRegion);
	
	int update(ScoreDO scoreRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
