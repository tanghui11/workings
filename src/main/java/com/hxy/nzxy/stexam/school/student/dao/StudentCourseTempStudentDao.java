package com.hxy.nzxy.stexam.school.student.dao;

import com.hxy.nzxy.stexam.domain.StudentCourseTempDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考生报考临时表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
@Mapper
public interface StudentCourseTempStudentDao {

	StudentCourseTempDO get(Long id);
	
	List<StudentCourseTempDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentCourseTempDO studentCourseTempStudent);
	
	int update(StudentCourseTempDO studentCourseTempStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
