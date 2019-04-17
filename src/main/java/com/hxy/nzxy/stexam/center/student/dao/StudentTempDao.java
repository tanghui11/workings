package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.domain.StudentTempDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 考生基本信息临时表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:26
 */
@Mapper
public interface StudentTempDao {

	StudentTempDO get(String ksZkz);
	
	List<StudentTempDO> list(Map<String, Object> map);

	List<StudentTempDO> listW(List<StudentTempDO> list);

	int count(Map<String, Object> map);
	
	int save(StudentTempDO studentTemp);

	int ZSsave(StudentTempDO studentTemp);
	
	int update(StudentTempDO studentTemp);
	
	int remove(String type);

	int moveOut(@Param("studentid") String studentid);

	int batchRemove(String[] types);

    Integer delete(@Param("studentid") String studentid);

	int isNotExist(@Param("studentid") String studentid);
}
