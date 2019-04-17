package com.hxy.nzxy.stexam.region.region.dao;

import com.hxy.nzxy.stexam.domain.SeatArrangeHisDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 座次安排_历史表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
@Mapper
public interface SeatArrangeHisRegionDao {

	SeatArrangeHisDO get(Long id);
	
	List<SeatArrangeHisDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SeatArrangeHisDO seatArrangeHisRegion);
	
	int update(SeatArrangeHisDO seatArrangeHisRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
