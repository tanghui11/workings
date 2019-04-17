package com.hxy.nzxy.stexam.region.region.dao;

import com.hxy.nzxy.stexam.domain.RoomArrangeHisDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考场编排_历史表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
@Mapper
public interface RoomArrangeHisRegionDao {

	RoomArrangeHisDO get(Long id);
	
	List<RoomArrangeHisDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(RoomArrangeHisDO roomArrangeHisRegion);
	
	int update(RoomArrangeHisDO roomArrangeHisRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
