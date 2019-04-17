package com.hxy.nzxy.stexam.center.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.school.dao.SchoolSpecialityRegDao;
import com.hxy.nzxy.stexam.domain.SchoolSpecialityRegDO;
import com.hxy.nzxy.stexam.center.school.service.SchoolSpecialityRegService;



@Service
public class SchoolSpecialityRegServiceImpl implements SchoolSpecialityRegService {
	@Autowired
	private SchoolSpecialityRegDao schoolSpecialityRegDao;
	
	@Override
	public SchoolSpecialityRegDO get(Long id){
		return schoolSpecialityRegDao.get(id);
	}
	
	@Override
	public List<SchoolSpecialityRegDO> list(Map<String, Object> map){
		return schoolSpecialityRegDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return schoolSpecialityRegDao.count(map);
	}
	
	@Override
	public int save(SchoolSpecialityRegDO schoolSpecialityReg){
		return schoolSpecialityRegDao.save(schoolSpecialityReg);
	}
	
	@Override
	public int update(SchoolSpecialityRegDO schoolSpecialityReg){
		return schoolSpecialityRegDao.update(schoolSpecialityReg);
	}
	
	@Override
	public int remove(Long id){
		return schoolSpecialityRegDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return schoolSpecialityRegDao.batchRemove(ids);
	}

	@Override
	public int batchUpdateAudit(Long[] ids, String auditStatus) {
		Map  map =new HashMap();
		map.put("array",ids);
		map.put("auditStatus",auditStatus);
		return schoolSpecialityRegDao.batchUpdateAudit(map);
	}

	@Override
	public int updateAudit(Map<String, Object> params) {
		return schoolSpecialityRegDao.updateAudit(params);
	}

	@Override
	public List<SchoolSpecialityRegDO> listSchoolSpeciality(Map<String, Object> map) {
		return schoolSpecialityRegDao.listSchoolSpeciality(map);
	}

	@Override
	public int listSchoolcount(Map<String, Object> map) {
		return schoolSpecialityRegDao.listSchoolcount(map);
	}

}
