package com.hxy.nzxy.stexam.region.region.dao;

import com.hxy.nzxy.stexam.domain.ExamRoomHisDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考场_历史表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:33
 */
@Mapper
public interface ExamRoomHisRegionDao {

	ExamRoomHisDO get(Long id);
	
	List<ExamRoomHisDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ExamRoomHisDO examRoomHisRegion);
	
	int update(ExamRoomHisDO examRoomHisRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
