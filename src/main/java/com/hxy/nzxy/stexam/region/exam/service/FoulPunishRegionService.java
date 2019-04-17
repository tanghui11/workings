package com.hxy.nzxy.stexam.region.exam.service;

import com.hxy.nzxy.stexam.domain.FoulPunishDO;

import java.util.List;
import java.util.Map;

/**
 * 犯规及处罚设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-11 10:32:23
 */
public interface FoulPunishRegionService {
	
	FoulPunishDO get(Long id);
	
	List<FoulPunishDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FoulPunishDO foulPunishRegion);
	
	int update(FoulPunishDO foulPunishRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
