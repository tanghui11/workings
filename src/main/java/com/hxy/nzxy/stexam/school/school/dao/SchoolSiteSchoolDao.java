package com.hxy.nzxy.stexam.school.school.dao;

import com.hxy.nzxy.stexam.domain.SchoolSiteDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 办学地区管理
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 20:36:41
 */
@Mapper
public interface SchoolSiteSchoolDao {

	SchoolSiteDO get(Long id);
	
	List<SchoolSiteDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SchoolSiteDO schoolSiteSchool);
	
	int update(SchoolSiteDO schoolSiteSchool);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
