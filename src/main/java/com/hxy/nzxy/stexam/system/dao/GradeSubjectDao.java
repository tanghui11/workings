package com.hxy.nzxy.stexam.system.dao;

import com.hxy.nzxy.stexam.system.domain.GradeSubjectDO;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.system.domain.SubjectDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 年级科目
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-31 19:21:45
 */
@Mapper
public interface GradeSubjectDao {

	GradeSubjectDO get(String id);
	
	List<GradeSubjectDO> list(Map<String, Object> map);

	List<SubjectDO> querySubject(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(GradeSubjectDO gradeSubject);
	
	int update(GradeSubjectDO gradeSubject);
	
	int remove(String id);
	
	int batchSave(List<GradeSubjectDO> gradeSubjectDOList);
}
