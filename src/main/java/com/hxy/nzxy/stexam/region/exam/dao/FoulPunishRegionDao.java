package com.hxy.nzxy.stexam.region.exam.dao;

import com.hxy.nzxy.stexam.domain.FoulPunishDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 犯规及处罚设置
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-11 10:32:23
 */
@Mapper
public interface FoulPunishRegionDao {

	FoulPunishDO get(Long id);
	
	List<FoulPunishDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FoulPunishDO foulPunishRegion);
	
	int update(FoulPunishDO foulPunishRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
