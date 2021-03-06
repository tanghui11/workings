package com.hxy.nzxy.stexam.center.exam.service;

import com.hxy.nzxy.stexam.domain.EducateLengthDO;

import java.util.List;
import java.util.Map;

/**
 * 学制定义
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 15:58:19
 */
public interface EducateLengthService {
	
	EducateLengthDO get(Long id);
	
	List<EducateLengthDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(EducateLengthDO educateLength);
	
	int update(EducateLengthDO educateLength);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
