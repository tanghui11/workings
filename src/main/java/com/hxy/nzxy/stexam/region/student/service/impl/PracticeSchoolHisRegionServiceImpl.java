package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.PracticeSchoolHisRegionDao;
import com.hxy.nzxy.stexam.domain.PracticeSchoolHisDO;
import com.hxy.nzxy.stexam.region.student.service.PracticeSchoolHisRegionService;



@Service
public class PracticeSchoolHisRegionServiceImpl implements PracticeSchoolHisRegionService {
	@Autowired
	private PracticeSchoolHisRegionDao practiceSchoolHisRegionDao;
	
	@Override
	public PracticeSchoolHisDO get(Long id){
		return practiceSchoolHisRegionDao.get(id);
	}
	
	@Override
	public List<PracticeSchoolHisDO> list(Map<String, Object> map){
		return practiceSchoolHisRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return practiceSchoolHisRegionDao.count(map);
	}
	
	@Override
	public int save(PracticeSchoolHisDO practiceSchoolHisRegion){
		return practiceSchoolHisRegionDao.save(practiceSchoolHisRegion);
	}
	
	@Override
	public int update(PracticeSchoolHisDO practiceSchoolHisRegion){
		return practiceSchoolHisRegionDao.update(practiceSchoolHisRegion);
	}
	
	@Override
	public int remove(Long id){
		return practiceSchoolHisRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return practiceSchoolHisRegionDao.batchRemove(ids);
	}
	
}
