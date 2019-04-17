package com.hxy.nzxy.stexam.school.school.dao;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.CollegeSpecialityDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 专业管理对应
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 20:36:40
 */
@Mapper
public interface CollegeSpecialitySchoolDao {

	CollegeSpecialityDO get(Long id);
	
	List<CollegeSpecialityDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CollegeSpecialityDO collegeSpecialitySchool);
	
	int update(CollegeSpecialityDO collegeSpecialitySchool);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<CollegeSpecialityDO> ylist(Query query);

    int ycount(Query query);

	int dcount(Query query);

	List<CollegeSpecialityDO> dlist(Query query);
}
