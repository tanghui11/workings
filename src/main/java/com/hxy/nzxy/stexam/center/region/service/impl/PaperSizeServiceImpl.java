package com.hxy.nzxy.stexam.center.region.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.region.dao.PaperSizeDao;
import com.hxy.nzxy.stexam.domain.PaperSizeDO;
import com.hxy.nzxy.stexam.center.region.service.PaperSizeService;



@Service
public class PaperSizeServiceImpl implements PaperSizeService {
	@Autowired
	private PaperSizeDao paperSizeDao;
	
	@Override
	public PaperSizeDO get(Long id){
		return paperSizeDao.get(id);
	}
	
	@Override
	public List<PaperSizeDO> list(Map<String, Object> map){
		return paperSizeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return paperSizeDao.count(map);
	}
	
	@Override
	public int save(PaperSizeDO paperSize){
		return paperSizeDao.save(paperSize);
	}
	
	@Override
	public int update(PaperSizeDO paperSize){
		return paperSizeDao.update(paperSize);
	}
	
	@Override
	public int remove(Long id){
		return paperSizeDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return paperSizeDao.batchRemove(ids);
	}
	
}
