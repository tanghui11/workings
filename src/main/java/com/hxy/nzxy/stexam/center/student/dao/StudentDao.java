package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.domain.StudentDO;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.domain.StudentTempDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考生基本信息表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
@Mapper
public interface StudentDao {

	StudentDO get(String id);
	
	List<StudentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentDO student);
	
	int update(StudentDO student);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
	int batchUpdateAudit(Map params);
	int updateAudit(Map<String, Object> params);

    int batchUpdateTx(Map map);

    List<StudentDO> listCZ(List<StudentDO> userKnowledgeBaseList);

	List<StudentDO> listW(List<StudentTempDO> userKnowledgeBaseList);

	void saveBatch(List<StudentDO> userKnowledgeBaseList);

    List<StudentDO> listCZStudentid(List<StudentDO> userKnowledgeBaseList);
}
