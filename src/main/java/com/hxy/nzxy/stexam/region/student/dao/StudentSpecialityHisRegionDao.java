package com.hxy.nzxy.stexam.region.student.dao;

import com.hxy.nzxy.stexam.domain.StudentSpecialityHisDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考生报考专业信息表_历史表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
@Mapper
public interface StudentSpecialityHisRegionDao {

	StudentSpecialityHisDO get(Long id);
	
	List<StudentSpecialityHisDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentSpecialityHisDO studentSpecialityHisRegion);
	
	int update(StudentSpecialityHisDO studentSpecialityHisRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
