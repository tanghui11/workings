package com.hxy.nzxy.stexam.region.student.dao;

import com.hxy.nzxy.stexam.domain.StudentTempDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考生基本信息临时表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:15
 */
@Mapper
public interface StudentTempRegionDao {

	StudentTempDO get(String type);
	
	List<StudentTempDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentTempDO studentTempRegion);
	
	int update(StudentTempDO studentTempRegion);
	
	int remove(String type);
	
	int batchRemove(String[] types);
}
