package com.hxy.nzxy.stexam.center.region.dao;

import com.hxy.nzxy.stexam.domain.ExamRoomDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考场
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:18
 */
@Mapper
public interface ExamRoomDao {

	ExamRoomDO get(Long id);
	
	List<ExamRoomDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExamRoomDO examRoom);
	
	int update(ExamRoomDO examRoom);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
