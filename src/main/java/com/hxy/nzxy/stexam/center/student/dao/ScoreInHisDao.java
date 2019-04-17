package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreInHisDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 转入成绩_历史表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
@Mapper
public interface ScoreInHisDao {

	ScoreInHisDO get(Long id);
	
	List<ScoreInHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreInHisDO scoreInHis);
	
	int update(ScoreInHisDO scoreInHis);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
