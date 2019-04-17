package com.hxy.nzxy.stexam.region.region.service.impl;

import com.hxy.nzxy.stexam.domain.SpecialityDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.region.dao.RegionSpecialityRegionDao;
import com.hxy.nzxy.stexam.domain.RegionSpecialityDO;
import com.hxy.nzxy.stexam.region.region.service.RegionSpecialityRegionService;



@Service
public class RegionSpecialityRegionServiceImpl implements RegionSpecialityRegionService {
	@Autowired
	private RegionSpecialityRegionDao regionSpecialityRegionDao;
	
	@Override
	public RegionSpecialityDO get(Long id){
		return regionSpecialityRegionDao.get(id);
	}
	
	@Override
	public List<RegionSpecialityDO> list(Map<String, Object> map){
		return regionSpecialityRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return regionSpecialityRegionDao.count(map);
	}
	
	@Override
	public int save(RegionSpecialityDO regionSpecialityRegion){
		return regionSpecialityRegionDao.save(regionSpecialityRegion);
	}
	
	@Override
	public int update(RegionSpecialityDO regionSpecialityRegion){
		return regionSpecialityRegionDao.update(regionSpecialityRegion);
	}
	
	@Override
	public int remove(Long id){
		return regionSpecialityRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return regionSpecialityRegionDao.batchRemove(ids);
	}

    @Override
    public List<RegionSpecialityDO> regionSpecialitylist(Map<String, Object> params) { return regionSpecialityRegionDao.regionSpecialitylist(params);}

	@Override
	public List<SpecialityDO> planamelist(Map<String, Object> params) { return regionSpecialityRegionDao.planamelist(params); }

}
