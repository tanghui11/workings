package com.hxy.nzxy.stexam.school.student.dao;

import com.hxy.nzxy.stexam.domain.StudentDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考生基本信息表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
@Mapper
public interface StudentStudentDao {

	StudentDO get(String id);
	
	List<StudentDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentDO studentStudent);
	
	int update(StudentDO studentStudent);
	int updateNMD(StudentDO studentStudent);

	int remove(String id);
	
	int batchRemove(String[] ids);

    List<StudentDO> listCZ(List<StudentDO> userKnowledgeBaseList);

	List<StudentDO> listCZStudentid(List<StudentDO> userKnowledgeBaseList);

	void saveBatch(List<StudentDO> userKnowledgeBaseList);
}
