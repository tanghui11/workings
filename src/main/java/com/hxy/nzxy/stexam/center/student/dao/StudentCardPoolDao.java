package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.domain.StudentCardPoolDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 准考证打印池
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
@Mapper
public interface StudentCardPoolDao {

	StudentCardPoolDO get(String id);
	
	List<StudentCardPoolDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentCardPoolDO studentCardPool);
	
	int update(StudentCardPoolDO studentCardPool);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
