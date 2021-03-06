package com.hxy.nzxy.stexam.center.exam.service;

import com.hxy.nzxy.stexam.domain.FoulPunishDO;

import java.util.List;
import java.util.Map;

/**
 * 犯规及处罚设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 15:58:19
 */
public interface FoulPunishService {
	
	FoulPunishDO get(Long id);
	
	List<FoulPunishDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FoulPunishDO foulPunish);
	
	int update(FoulPunishDO foulPunish);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
