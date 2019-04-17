package com.hxy.nzxy.stexam.center.region.service;

import com.hxy.nzxy.stexam.domain.RoomArrangeHisDO;

import java.util.List;
import java.util.Map;

/**
 * 考场编排_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:19
 */
public interface RoomArrangeHisService {
	
	RoomArrangeHisDO get(Long id);
	
	List<RoomArrangeHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RoomArrangeHisDO roomArrangeHis);
	
	int update(RoomArrangeHisDO roomArrangeHis);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
