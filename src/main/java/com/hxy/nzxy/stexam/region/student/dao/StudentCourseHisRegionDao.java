package com.hxy.nzxy.stexam.region.student.dao;

import com.hxy.nzxy.stexam.domain.StudentCourseHisDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考生报考表_历史表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
@Mapper
public interface StudentCourseHisRegionDao {

	StudentCourseHisDO get(Long id);
	
	List<StudentCourseHisDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentCourseHisDO studentCourseHisRegion);
	
	int update(StudentCourseHisDO studentCourseHisRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
