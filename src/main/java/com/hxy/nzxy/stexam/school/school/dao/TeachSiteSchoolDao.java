package com.hxy.nzxy.stexam.school.school.dao;

import com.hxy.nzxy.stexam.domain.TeachSiteDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 教学点管理
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 20:36:41
 */
@Mapper
public interface TeachSiteSchoolDao {

	TeachSiteDO get(Long id);
	
	List<TeachSiteDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TeachSiteDO teachSiteSchool);
	
	int update(TeachSiteDO teachSiteSchool);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
