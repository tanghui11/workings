package com.hxy.nzxy.stexam.region.region.dao;

import com.hxy.nzxy.stexam.domain.StudentFoulDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考试违规
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
@Mapper
public interface StudentFoulRegionDao {

	StudentFoulDO get(Long studentCourseid);
	
	List<StudentFoulDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentFoulDO studentFoulRegion);
	
	int update(StudentFoulDO studentFoulRegion);
	
	int remove(Long student_courseid);
	
	int batchRemove(Long[] studentCourseids);
}
