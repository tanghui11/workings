package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.domain.StudentChangeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考生信息变更表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
@Mapper
public interface StudentChangeDao {

	StudentChangeDO get(Long id);
	
	List<StudentChangeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentChangeDO studentChange);
	
	int update(StudentChangeDO studentChange);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    int getSpecialityid(Long id);
}
