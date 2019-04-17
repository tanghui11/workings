package com.hxy.nzxy.stexam.region.student.dao;

import com.hxy.nzxy.stexam.domain.StudentRegHisDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考生注册_历史表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
@Mapper
public interface StudentRegHisRegionDao {

	StudentRegHisDO get(Long id);
	
	List<StudentRegHisDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentRegHisDO studentRegHisRegion);
	
	int update(StudentRegHisDO studentRegHisRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
