package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreOldDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 老课程成绩
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
@Mapper
public interface ScoreOldDao {

	ScoreOldDO get(Long id);
	
	List<ScoreOldDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreOldDO scoreOld);
	
	int update(ScoreOldDO scoreOld);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	int updateScore(Map<String,Object> map);
}
