package com.hxy.nzxy.stexam.school.student.dao;

import com.hxy.nzxy.stexam.domain.StudentTempDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考生基本信息临时表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:45
 */
@Mapper
public interface StudentTempStudentDao {

	StudentTempDO get(String type);
	
	List<StudentTempDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentTempDO studentTempStudent);
	
	int update(StudentTempDO studentTempStudent);
	
	int remove(String type);
	
	int batchRemove(String[] types);
}
