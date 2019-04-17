package com.hxy.nzxy.stexam.region.student.service.impl;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.SpecialityCourseDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.PracticeSchoolRegionDao;
import com.hxy.nzxy.stexam.domain.PracticeSchoolDO;
import com.hxy.nzxy.stexam.region.student.service.PracticeSchoolRegionService;



@Service
public class PracticeSchoolRegionServiceImpl implements PracticeSchoolRegionService {
	@Autowired
	private PracticeSchoolRegionDao practiceSchoolRegionDao;
	
	@Override
	public PracticeSchoolDO get(Long id){
		return practiceSchoolRegionDao.get(id);
	}
	
	@Override
	public List<PracticeSchoolDO> list(Map<String, Object> map){
		return practiceSchoolRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return practiceSchoolRegionDao.count(map);
	}
	
	@Override
	public int save(PracticeSchoolDO practiceSchoolRegion){
		return practiceSchoolRegionDao.save(practiceSchoolRegion);
	}
	
	@Override
	public int update(PracticeSchoolDO practiceSchoolRegion){
		return practiceSchoolRegionDao.update(practiceSchoolRegion);
	}
	
	@Override
	public int remove(Long id){
		return practiceSchoolRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return practiceSchoolRegionDao.batchRemove(ids);
	}

	@Override
	public List<SpecialityCourseDO> listCourse(Query query) {
		return practiceSchoolRegionDao.listCourse(query);
	}

	@Override
	public int countCourse(Query query) {
		return practiceSchoolRegionDao.countCourse(query);
	}

}
