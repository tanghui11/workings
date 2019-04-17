package com.hxy.nzxy.stexam.school.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreReplaceDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 免考成绩
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
@Mapper
public interface ScoreReplaceStudentDao {

	ScoreReplaceDO get(Long id);
	
	List<ScoreReplaceDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ScoreReplaceDO scoreReplaceStudent);
	
	int update(ScoreReplaceDO scoreReplaceStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
