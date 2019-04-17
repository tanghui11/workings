package com.hxy.nzxy.stexam.region.region.service;

import com.hxy.nzxy.stexam.domain.RegionSpecialityDO;
import com.hxy.nzxy.stexam.domain.SpecialityDO;

import java.util.List;
import java.util.Map;

/**
 * 考区专业课程报考
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
public interface RegionSpecialityRegionService {
	
	RegionSpecialityDO get(Long id);
	
	List<RegionSpecialityDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RegionSpecialityDO regionSpecialityRegion);
	
	int update(RegionSpecialityDO regionSpecialityRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<RegionSpecialityDO> regionSpecialitylist(Map<String, Object> params);

	List<SpecialityDO> planamelist(Map<String, Object> params);
}
