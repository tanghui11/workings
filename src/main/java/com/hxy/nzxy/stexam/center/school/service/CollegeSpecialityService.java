package com.hxy.nzxy.stexam.center.school.service;

import com.hxy.nzxy.stexam.domain.CollegeSpecialityDO;
import com.hxy.nzxy.stexam.domain.CollegeSpecialityVO;

import java.util.List;
import java.util.Map;

/**
 * 专业管理对应
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:27
 */
public interface CollegeSpecialityService {
	
	CollegeSpecialityDO get(Long id);
	
	List<CollegeSpecialityDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CollegeSpecialityDO collegeSpeciality);
	
	int update(CollegeSpecialityDO collegeSpeciality);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<CollegeSpecialityVO> listCollegeSpeciality(Map<String,Object> params);
}
