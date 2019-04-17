package com.hxy.nzxy.stexam.center.region.dao;

import com.hxy.nzxy.stexam.domain.GroupsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 集体设置
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:18
 */
@Mapper
public interface GroupsDao {

	GroupsDO get(Long id);
	
	List<GroupsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(GroupsDO groups);
	
	int update(GroupsDO groups);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    void saveBatch(List<GroupsDO> userKnowledgeBaseList);

	List<GroupsDO> listCZ(List<GroupsDO> userKnowledgeBaseList);
}
