package com.hxy.nzxy.stexam.region.region.dao;

import com.hxy.nzxy.stexam.domain.GroupsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 集体设置
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
@Mapper
public interface GroupsRegionDao {

	GroupsDO get(Long id);
	
	List<GroupsDO> list(Map<String,Object> map);

	List<GroupsDO> listL(List<GroupsDO> groupsDOList);
	
	int count(Map<String,Object> map);
	
	int save(GroupsDO groupsRegion);
	
	int update(GroupsDO groupsRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
