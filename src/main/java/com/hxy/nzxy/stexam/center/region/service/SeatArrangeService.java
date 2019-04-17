package com.hxy.nzxy.stexam.center.region.service;

import com.hxy.nzxy.stexam.domain.SeatArrangeDO;

import java.util.List;
import java.util.Map;

/**
 * 座次安排
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:19
 */
public interface SeatArrangeService {
	
	SeatArrangeDO get(Long id);
	
	List<SeatArrangeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SeatArrangeDO seatArrange);
	
	int update(SeatArrangeDO seatArrange);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
