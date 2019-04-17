package com.hxy.nzxy.stexam.region.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreOldHisDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 老课程成绩_历史表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
@Mapper
public interface ScoreOldHisRegionDao {

	ScoreOldHisDO get(Long id);
	
	List<ScoreOldHisDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ScoreOldHisDO scoreOldHisRegion);
	
	int update(ScoreOldHisDO scoreOldHisRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
