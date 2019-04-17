package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreHisDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考生成绩表_历史表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
@Mapper
public interface ScoreHisDao {

	ScoreHisDO get(Long id);
	
	List<ScoreHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreHisDO scoreHis);
	
	int update(ScoreHisDO scoreHis);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
