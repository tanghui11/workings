package com.hxy.nzxy.stexam.center.region.dao;

import com.hxy.nzxy.stexam.domain.RegionDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考区设置
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:18
 */
@Mapper
public interface RegionDao {

	RegionDO get(Long id);
	
	List<RegionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RegionDO region);
	
	int update(RegionDO region);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    int updateParentName(RegionDO region);

    List<RegionDO> listK(List<RegionDO> userKnowledgeBaseList);

}
