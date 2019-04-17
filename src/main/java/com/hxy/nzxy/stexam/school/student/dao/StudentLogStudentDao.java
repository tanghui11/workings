package com.hxy.nzxy.stexam.school.student.dao;

import com.hxy.nzxy.stexam.domain.StudentLogDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考生变更日志
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
@Mapper
public interface StudentLogStudentDao {

	StudentLogDO get(String changeType);
	
	List<StudentLogDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentLogDO studentLogStudent);
	
	int update(StudentLogDO studentLogStudent);
	
	int remove(String change_type);
	
	int batchRemove(String[] changeTypes);
}
