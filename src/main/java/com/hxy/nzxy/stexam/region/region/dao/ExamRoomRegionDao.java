package com.hxy.nzxy.stexam.region.region.dao;

import com.hxy.nzxy.stexam.domain.ExamRoomDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考场
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:33
 */
@Mapper
public interface ExamRoomRegionDao {

	ExamRoomDO get(Long id);
	
	List<ExamRoomDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ExamRoomDO examRoomRegion);
	
	int update(ExamRoomDO examRoomRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<ExamRoomDO> getSite(Map<String, Object> map);

	int batchSave(List list);
}
