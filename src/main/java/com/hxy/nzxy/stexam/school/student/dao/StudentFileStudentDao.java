package com.hxy.nzxy.stexam.school.student.dao;

import com.hxy.nzxy.stexam.domain.StudentDO;
import com.hxy.nzxy.stexam.domain.StudentRegDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 考生基本信息表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
@Mapper
public interface StudentFileStudentDao {

	StudentDO get(String id);

	List<StudentDO> list(Map<String, Object> map);
	List<StudentDO> listQu(Map<String, Object> map);

	List<StudentRegDO> listReg(Map<String, Object> map);

	int count(Map<String, Object> map);
	int countQu(Map<String, Object> map);

	int save(StudentDO studentStudent);
	
	int update(StudentDO studentStudent);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

    int updateTx(String[] ids);

	int updateTx1(String[] ids);
}
