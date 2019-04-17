package com.hxy.nzxy.stexam.region.student.dao;

import com.hxy.nzxy.stexam.domain.StudentChangeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考生信息变更表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
@Mapper
public interface StudentChangeRegionDao {

	StudentChangeDO get(Long id);
	
	List<StudentChangeDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentChangeDO studentChangeRegion);
	
	int update(StudentChangeDO studentChangeRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
