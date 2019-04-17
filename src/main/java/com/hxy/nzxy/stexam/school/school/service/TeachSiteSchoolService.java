package com.hxy.nzxy.stexam.school.school.service;

import com.hxy.nzxy.stexam.domain.TeachSiteDO;

import java.util.List;
import java.util.Map;

/**
 * 教学点管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 20:36:41
 */
public interface TeachSiteSchoolService {
	
	TeachSiteDO get(Long id);
	
	List<TeachSiteDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TeachSiteDO teachSiteSchool);
	
	int update(TeachSiteDO teachSiteSchool);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
