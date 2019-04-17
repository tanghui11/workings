package com.hxy.nzxy.stexam.region.student.service;

import com.hxy.nzxy.stexam.domain.ScoreTempDO;

import java.util.List;
import java.util.Map;

/**
 * 登分表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
public interface ScoreTempRegionService {
	
	ScoreTempDO get(Long seatArrangeid);
	
	List<ScoreTempDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreTempDO scoreTempRegion);
	
	int update(ScoreTempDO scoreTempRegion);
	
	int remove(Long seatArrangeid);
	
	int batchRemove(Long[] seatArrangeids);
}
