package com.hxy.nzxy.stexam.center.region.dao;

import com.hxy.nzxy.stexam.domain.ExamSiteDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考点维护
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:18
 */
@Mapper
public interface ExamSiteDao {

	ExamSiteDO get(Long id);
	
	List<ExamSiteDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExamSiteDO examSite);
	
	int update(ExamSiteDO examSite);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
