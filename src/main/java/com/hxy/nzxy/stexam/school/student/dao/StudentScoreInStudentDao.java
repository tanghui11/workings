package com.hxy.nzxy.stexam.school.student.dao;

import com.hxy.nzxy.stexam.domain.StudentScoreInDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 转入成绩
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:45
 */
@Mapper
public interface StudentScoreInStudentDao {

	StudentScoreInDO get(Long id);
	
	List<StudentScoreInDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentScoreInDO studentScoreInStudent);
	
	int update(StudentScoreInDO studentScoreInStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
