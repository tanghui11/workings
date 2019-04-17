package com.hxy.nzxy.stexam.school.school.service;

import com.hxy.nzxy.stexam.domain.SchoolSiteDO;

import java.util.List;
import java.util.Map;

/**
 * 办学地区管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 20:36:41
 */
public interface SchoolSiteSchoolService {
	
	SchoolSiteDO get(Long id);
	
	List<SchoolSiteDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SchoolSiteDO schoolSiteSchool);
	
	int update(SchoolSiteDO schoolSiteSchool);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
