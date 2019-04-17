package com.hxy.nzxy.stexam.region.student.dao;

import com.hxy.nzxy.stexam.domain.StudentCardPoolDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 准考证打印池
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
@Mapper
public interface StudentCardPoolRegionDao {

	StudentCardPoolDO get(String id);
	
	List<StudentCardPoolDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentCardPoolDO studentCardPoolRegion);
	
	int update(StudentCardPoolDO studentCardPoolRegion);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
