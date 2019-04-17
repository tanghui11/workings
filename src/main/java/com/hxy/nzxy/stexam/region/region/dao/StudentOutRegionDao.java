package com.hxy.nzxy.stexam.region.region.dao;

import com.hxy.nzxy.stexam.domain.StudentOutDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考绩转出
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
@Mapper
public interface StudentOutRegionDao {

	StudentOutDO get(Long id);
	
	List<StudentOutDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentOutDO studentOutRegion);
	
	int update(StudentOutDO studentOutRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
