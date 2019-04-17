package com.hxy.nzxy.stexam.school.school.service.impl;

import com.hxy.nzxy.stexam.center.plan.dao.SpecialityRecordDao;
import com.hxy.nzxy.stexam.center.student.dao.StudentSpecialityDao;
import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.SpecialityRecordDO;
import com.hxy.nzxy.stexam.domain.StudentSpecialityDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.school.dao.SchoolSpecialityRegSchoolDao;
import com.hxy.nzxy.stexam.domain.SchoolSpecialityRegDO;
import com.hxy.nzxy.stexam.school.school.service.SchoolSpecialityRegSchoolService;



@Service
public class SchoolSpecialityRegSchoolServiceImpl implements SchoolSpecialityRegSchoolService {
	@Autowired
	private SchoolSpecialityRegSchoolDao schoolSpecialityRegSchoolDao;
	@Autowired
	private SpecialityRecordDao specialityRecordDao;
	@Autowired
	private StudentSpecialityDao studentSpecialityDao;
	
	@Override
	public SchoolSpecialityRegDO get(Long id){
		return schoolSpecialityRegSchoolDao.get(id);
	}
	
	@Override
	public List<SchoolSpecialityRegDO> list(Map<String, Object> map){
		return schoolSpecialityRegSchoolDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return schoolSpecialityRegSchoolDao.count(map);
	}
	
	@Override
	public int save(SchoolSpecialityRegDO schoolSpecialityRegSchool){
		int save = schoolSpecialityRegSchoolDao.save(schoolSpecialityRegSchool);
		//将这批申请开源的专科学生报本科专业
		if(schoolSpecialityRegSchool.getClassify().equals("a")){
				//查询出这批学生报的专业信息
			SpecialityRecordDO specialityRecordDO = specialityRecordDao.get(schoolSpecialityRegSchool.getSpecialityRecordid());
			//查出这批开考专业的学生
			Map map=new HashMap();
			map.put("schoolSpecialityRegid",schoolSpecialityRegSchool.getId());
			List<StudentSpecialityDO> StudentSpecialityDO = studentSpecialityDao.listSpeciality(map);
			//设置初始数据
			for (StudentSpecialityDO studentSpecialityDO:
					StudentSpecialityDO ) {
				studentSpecialityDO.setSpecialityid(specialityRecordDO.getSpecialityid());
				studentSpecialityDO.setSpecialityRecordid(schoolSpecialityRegSchool.getSpecialityRecordid());
				studentSpecialityDO.setSchoolSpecialityRegid(schoolSpecialityRegSchool.getId());
			};

			//批量报考这批学生
			studentSpecialityDao.saveBatch(StudentSpecialityDO);
		}




		return save;
	}
	
	@Override
	public int update(SchoolSpecialityRegDO schoolSpecialityRegSchool){
		return schoolSpecialityRegSchoolDao.update(schoolSpecialityRegSchool);
	}
	
	@Override
	public int remove(Long id){
		return schoolSpecialityRegSchoolDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return schoolSpecialityRegSchoolDao.batchRemove(ids);
	}

	@Override
	public SchoolSpecialityRegDO getSelect(Long schoolSpecialityRegid) {
		return schoolSpecialityRegSchoolDao.getSelect(schoolSpecialityRegid);
	}

	@Override
	public List<SchoolSpecialityRegDO> listBk(Query query) {
		return schoolSpecialityRegSchoolDao.listBk(query);
	}

	@Override
	public int countBk(Query query) {
		return schoolSpecialityRegSchoolDao.countBk(query);
	}

}
