package com.hxy.nzxy.stexam.center.region.dao;

import com.hxy.nzxy.stexam.domain.StudentOutDO;
import com.hxy.nzxy.stexam.domain.StudentOutSpecialityDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 考绩转出
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:19
 */
@Mapper
public interface StudentOutDao {

	StudentOutDO get(Long id);
	
	List<StudentOutDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);

	int studentOutSpecialityCount (Map<String, Object> map);
	
	int save(StudentOutDO studentOut);
	
	int update(StudentOutDO studentOut);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    int out(Long id);

	List<StudentOutSpecialityDO> studentOutSpecialityList(Map<String, Object> map);
}
