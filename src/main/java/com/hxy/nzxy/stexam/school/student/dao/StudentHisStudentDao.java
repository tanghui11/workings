package com.hxy.nzxy.stexam.school.student.dao;

import com.hxy.nzxy.stexam.domain.StudentHisDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考生基本信息表_历史表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
@Mapper
public interface StudentHisStudentDao {

	StudentHisDO get(String id);
	
	List<StudentHisDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentHisDO studentHisStudent);
	
	int update(StudentHisDO studentHisStudent);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
