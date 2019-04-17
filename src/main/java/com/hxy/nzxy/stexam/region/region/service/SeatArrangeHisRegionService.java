package com.hxy.nzxy.stexam.region.region.service;

import com.hxy.nzxy.stexam.domain.SeatArrangeHisDO;

import java.util.List;
import java.util.Map;

/**
 * 座次安排_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
public interface SeatArrangeHisRegionService {
	
	SeatArrangeHisDO get(Long id);
	
	List<SeatArrangeHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SeatArrangeHisDO seatArrangeHisRegion);
	
	int update(SeatArrangeHisDO seatArrangeHisRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
