package com.hxy.nzxy.stexam.center.region.service;

import com.hxy.nzxy.stexam.domain.ExamSiteSubmitHisDO;

import java.util.List;
import java.util.Map;

/**
 * 考点上报_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:18
 */
public interface ExamSiteSubmitHisService {
	
	ExamSiteSubmitHisDO get(Long id);
	
	List<ExamSiteSubmitHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExamSiteSubmitHisDO examSiteSubmitHis);
	
	int update(ExamSiteSubmitHisDO examSiteSubmitHis);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
