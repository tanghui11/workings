package com.hxy.nzxy.stexam.school.student.dao;

import com.hxy.nzxy.stexam.domain.StudentSpecialityHisDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考生报考专业信息表_历史表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:45
 */
@Mapper
public interface StudentSpecialityHisStudentDao {

	StudentSpecialityHisDO get(Long id);
	
	List<StudentSpecialityHisDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentSpecialityHisDO studentSpecialityHisStudent);
	
	int update(StudentSpecialityHisDO studentSpecialityHisStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
