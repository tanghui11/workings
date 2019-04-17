package com.hxy.nzxy.stexam.region.region.dao;

import com.hxy.nzxy.stexam.domain.SeatArrangeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 座次安排
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
@Mapper
public interface SeatArrangeRegionDao {

	SeatArrangeDO get(Long id);
	
	List<SeatArrangeDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SeatArrangeDO seatArrangeRegion);
	
	int update(SeatArrangeDO seatArrangeRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
