package com.hxy.nzxy.stexam.region.student.dao;

import com.hxy.nzxy.stexam.domain.StudentGradeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 毕业前考生信息修改表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
@Mapper
public interface StudentGradeRegionDao {

	StudentGradeDO get(String id);
	
	List<StudentGradeDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentGradeDO studentGradeRegion);
	
	int update(StudentGradeDO studentGradeRegion);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}