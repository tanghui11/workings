package com.hxy.nzxy.stexam.center.region.dao;

import com.hxy.nzxy.stexam.domain.RoomArrangeHisDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考场编排_历史表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:19
 */
@Mapper
public interface RoomArrangeHisDao {

	RoomArrangeHisDO get(Long id);
	
	List<RoomArrangeHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RoomArrangeHisDO roomArrangeHis);
	
	int update(RoomArrangeHisDO roomArrangeHis);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
