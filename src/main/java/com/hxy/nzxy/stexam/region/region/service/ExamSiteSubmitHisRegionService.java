package com.hxy.nzxy.stexam.region.region.service;

import com.hxy.nzxy.stexam.domain.ExamSiteSubmitHisDO;

import java.util.List;
import java.util.Map;

/**
 * 考点上报_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
public interface ExamSiteSubmitHisRegionService {
	
	ExamSiteSubmitHisDO get(Long id);
	
	List<ExamSiteSubmitHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExamSiteSubmitHisDO examSiteSubmitHisRegion);
	
	int update(ExamSiteSubmitHisDO examSiteSubmitHisRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
