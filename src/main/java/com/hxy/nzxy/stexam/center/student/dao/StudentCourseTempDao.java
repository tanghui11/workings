package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.domain.StudentCourseTempDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 考生报考临时表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
@Mapper
public interface StudentCourseTempDao {

	StudentCourseTempDO get(Long id);
	
	List<StudentCourseTempDO> list(Map<String, Object> map);

	List<StudentCourseTempDO> listsc(List<StudentCourseTempDO> list);
	
	int count(Map<String, Object> map);
	
	int save(StudentCourseTempDO studentCourseTemp);
	
	int update(StudentCourseTempDO studentCourseTemp);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	Long getExamCourseid(@Param("kssj") String kssj, @Param("kcdm") String kcdm);
}
