package com.hxy.nzxy.stexam.center.plan.service;

import com.hxy.nzxy.stexam.domain.ExamCodeDO;

import java.util.List;
import java.util.Map;

/**
 * 准考证流水号
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
public interface ExamCodeService {
	
	ExamCodeDO get(String fixed);
	
	List<ExamCodeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExamCodeDO examCode);
	
	int update(ExamCodeDO examCode);
	
	int remove(String fixed);
	
	int batchRemove(String[] fixeds);
}
