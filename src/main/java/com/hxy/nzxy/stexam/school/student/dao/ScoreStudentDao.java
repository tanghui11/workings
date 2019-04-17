package com.hxy.nzxy.stexam.school.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考生成绩表_历史表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:43
 */
@Mapper
public interface ScoreStudentDao {

	ScoreDO get(Long id);
	
	List<ScoreDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ScoreDO scoreStudent);
	
	int update(ScoreDO scoreStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
