package com.hxy.nzxy.stexam.region.student.dao;

import com.hxy.nzxy.stexam.domain.StudentSpecialityDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考生报考专业信息表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
@Mapper
public interface StudentSpecialityRegionDao {

	StudentSpecialityDO get(Long id);
	
	List<StudentSpecialityDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentSpecialityDO studentSpecialityRegion);
	
	int update(StudentSpecialityDO studentSpecialityRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
