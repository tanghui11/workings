package com.hxy.nzxy.stexam.region.student.dao;

import com.hxy.nzxy.stexam.domain.StudentPreDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考生基本信息_预报名
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
@Mapper
public interface StudentPreRegionDao {

	StudentPreDO get(Long id);
	
	List<StudentPreDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentPreDO studentPreRegion);
	
	int update(StudentPreDO studentPreRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
