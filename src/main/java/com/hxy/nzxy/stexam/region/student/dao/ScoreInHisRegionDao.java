package com.hxy.nzxy.stexam.region.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreInHisDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 转入成绩_历史表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
@Mapper
public interface ScoreInHisRegionDao {

	ScoreInHisDO get(Long id);
	
	List<ScoreInHisDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ScoreInHisDO scoreInHisRegion);
	
	int update(ScoreInHisDO scoreInHisRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
