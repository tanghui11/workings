package com.hxy.nzxy.stexam.region.region.dao;

import com.hxy.nzxy.stexam.domain.RegionDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考区设置
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
@Mapper
public interface RegionRegionDao {

	RegionDO get(Long id);
	
	List<RegionDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(RegionDO regionRegion);
	
	int update(RegionDO regionRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<RegionDO> listT(List<RegionDO> userKnowledgeBaseList);
}
