package com.hxy.nzxy.stexam.region.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreReplaceHisDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 免考成绩_历史表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
@Mapper
public interface ScoreReplaceHisRegionDao {

	ScoreReplaceHisDO get(Long id);
	
	List<ScoreReplaceHisDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ScoreReplaceHisDO scoreReplaceHisRegion);
	
	int update(ScoreReplaceHisDO scoreReplaceHisRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
