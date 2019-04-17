package com.hxy.nzxy.stexam.school.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreOldDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 老课程成绩
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
@Mapper
public interface ScoreOldStudentDao {

	ScoreOldDO get(Long id);
	
	List<ScoreOldDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ScoreOldDO scoreOldStudent);
	
	int update(ScoreOldDO scoreOldStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
