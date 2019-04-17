package com.hxy.nzxy.stexam.center.region.dao;

import com.hxy.nzxy.stexam.domain.ExamSiteSubmitDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考点上报
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:18
 */
@Mapper
public interface ExamSiteSubmitDao {

	ExamSiteSubmitDO get(Long id);
	
	List<ExamSiteSubmitDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExamSiteSubmitDO examSiteSubmit);
	
	int update(ExamSiteSubmitDO examSiteSubmit);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
