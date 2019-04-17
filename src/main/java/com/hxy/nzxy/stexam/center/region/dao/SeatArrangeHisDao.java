package com.hxy.nzxy.stexam.center.region.dao;

import com.hxy.nzxy.stexam.domain.SeatArrangeHisDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 座次安排_历史表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:19
 */
@Mapper
public interface SeatArrangeHisDao {

	SeatArrangeHisDO get(Long id);
	
	List<SeatArrangeHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SeatArrangeHisDO seatArrangeHis);
	
	int update(SeatArrangeHisDO seatArrangeHis);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
