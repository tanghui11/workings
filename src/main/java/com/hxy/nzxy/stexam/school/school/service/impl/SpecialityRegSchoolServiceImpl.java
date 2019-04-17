package com.hxy.nzxy.stexam.school.school.service.impl;

import com.hxy.nzxy.stexam.domain.SpecialityDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.school.dao.SpecialityRegSchoolDao;
import com.hxy.nzxy.stexam.domain.SpecialityRegDO;
import com.hxy.nzxy.stexam.school.school.service.SpecialityRegSchoolService;



@Service
public class SpecialityRegSchoolServiceImpl implements SpecialityRegSchoolService {
	@Autowired
	private SpecialityRegSchoolDao specialityRegSchoolDao;
	
	@Override
	public SpecialityRegDO get(Long id){
		return specialityRegSchoolDao.get(id);
	}
	
	@Override
	public List<SpecialityRegDO> list(Map<String, Object> map){
		return specialityRegSchoolDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return specialityRegSchoolDao.count(map);
	}
	
	@Override
	public int save(SpecialityRegDO specialityRegSchool){
		return specialityRegSchoolDao.save(specialityRegSchool);
	}
	
	@Override
	public int update(SpecialityRegDO specialityRegSchool){
		return specialityRegSchoolDao.update(specialityRegSchool);
	}
	
	@Override
	public int remove(Long id){
		return specialityRegSchoolDao.remove(id);
	}

	@Override
	public List<SpecialityRegDO> schoolSpeciality(Map<String, Object> map){
		return specialityRegSchoolDao.schoolSpeciality(map);
	}

	@Override
	public int getSpecialityCount(List<SpecialityRegDO> specialityRegSchoolList){
		return specialityRegSchoolDao.getSpecialityCount(specialityRegSchoolList);
	}
	@Override
	public List<SpecialityDO> getSpeciality(List<SpecialityRegDO> specialityRegSchoolList){
		return specialityRegSchoolDao.getSpeciality(specialityRegSchoolList);
	}

	@Override
	public List<SpecialityDO> getSpeciality2(Map<String,Object> map){
		return specialityRegSchoolDao.getSpeciality2(map);
	}

	@Override
	public int batchRemove(Long[] ids){
		return specialityRegSchoolDao.batchRemove(ids);
	}

	@Override
	public SpecialityRegDO getSpecialityRecordid(SpecialityRegDO specialityRegSchool) {
		return specialityRegSchoolDao.getSpecialityRecordid(specialityRegSchool);
	}

	@Override
	public void batchAuditStatus(Long[] ids, String status) {
		Map map =new HashMap();
		map.put("ids",ids);
		map.put("status",status);
		 specialityRegSchoolDao.batchAuditStatus(map);
	}


}
