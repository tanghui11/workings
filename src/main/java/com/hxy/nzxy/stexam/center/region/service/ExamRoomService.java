package com.hxy.nzxy.stexam.center.region.service;

import com.hxy.nzxy.stexam.domain.ExamRoomDO;

import java.util.List;
import java.util.Map;

/**
 * 考场
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:18
 */
public interface ExamRoomService {
	
	ExamRoomDO get(Long id);
	
	List<ExamRoomDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExamRoomDO examRoom);
	
	int update(ExamRoomDO examRoom);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
