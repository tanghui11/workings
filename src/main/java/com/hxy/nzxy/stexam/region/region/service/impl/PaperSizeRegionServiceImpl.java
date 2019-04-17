package com.hxy.nzxy.stexam.region.region.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.region.dao.PaperSizeRegionDao;
import com.hxy.nzxy.stexam.domain.PaperSizeDO;
import com.hxy.nzxy.stexam.region.region.service.PaperSizeRegionService;



@Service
public class PaperSizeRegionServiceImpl implements PaperSizeRegionService {
	@Autowired
	private PaperSizeRegionDao paperSizeRegionDao;
	
	@Override
	public PaperSizeDO get(Long id){
		return paperSizeRegionDao.get(id);
	}
	
	@Override
	public List<PaperSizeDO> list(Map<String, Object> map){
		return paperSizeRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return paperSizeRegionDao.count(map);
	}
	
	@Override
	public int save(PaperSizeDO paperSizeRegion){
		return paperSizeRegionDao.save(paperSizeRegion);
	}
	
	@Override
	public int update(PaperSizeDO paperSizeRegion){
		return paperSizeRegionDao.update(paperSizeRegion);
	}
	
	@Override
	public int remove(Long id){
		return paperSizeRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return paperSizeRegionDao.batchRemove(ids);
	}
	
}
