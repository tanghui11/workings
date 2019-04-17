package com.hxy.nzxy.stexam.school.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreChangeLogDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 成绩变更日志表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:43
 */
@Mapper
public interface ScoreChangeLogStudentDao {

	ScoreChangeLogDO get(String changeType);
	
	List<ScoreChangeLogDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ScoreChangeLogDO scoreChangeLogStudent);
	
	int update(ScoreChangeLogDO scoreChangeLogStudent);
	
	int remove(String change_type);
	
	int batchRemove(String[] changeTypes);
}
