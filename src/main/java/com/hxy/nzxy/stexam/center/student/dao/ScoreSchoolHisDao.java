package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreSchoolHisDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 校考成绩_历史表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
@Mapper
public interface ScoreSchoolHisDao {

	ScoreSchoolHisDO get(Long id);
	
	List<ScoreSchoolHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreSchoolHisDO scoreSchoolHis);
	
	int update(ScoreSchoolHisDO scoreSchoolHis);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
