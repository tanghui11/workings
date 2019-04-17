package com.hxy.nzxy.stexam.school.student.dao;

import com.hxy.nzxy.stexam.domain.StudentCardPoolDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 准考证打印池
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
@Mapper
public interface StudentCardPoolStudentDao {

	StudentCardPoolDO get(String id);
	
	List<StudentCardPoolDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentCardPoolDO studentCardPoolStudent);
	
	int update(StudentCardPoolDO studentCardPoolStudent);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
