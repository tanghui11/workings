package com.hxy.nzxy.stexam.center.exam.service.impl;

import com.hxy.nzxy.stexam.common.utils.DateUtils;
import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.common.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.exam.dao.PaperSchoolDao;
import com.hxy.nzxy.stexam.domain.PaperSchoolDO;
import com.hxy.nzxy.stexam.center.exam.service.PaperSchoolService;



@Service
public class PaperSchoolServiceImpl implements PaperSchoolService {
	@Autowired
	private PaperSchoolDao paperSchoolDao;
	
	@Override
	public PaperSchoolDO get(Long id){
		return paperSchoolDao.get(id);
	}
	
	@Override
	public List<PaperSchoolDO> list(Map<String, Object> map){
		return paperSchoolDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return paperSchoolDao.count(map);
	}
	
	@Override
	public int save(PaperSchoolDO paperSchool){
		return paperSchoolDao.save(paperSchool);
	}
	
	@Override
	public int update(PaperSchoolDO paperSchool){
		return paperSchoolDao.update(paperSchool);
	}
	
	@Override
	public int remove(Long id){
		return paperSchoolDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return paperSchoolDao.batchRemove(ids);
	}

	@Override
	public void set(String schoolid, List<PaperSchoolDO> conditions) {
		for (PaperSchoolDO paperSchool : conditions) {
			paperSchool.setOperator(ShiroUtils.getUserId().toString());
			paperSchool.setSchoolid(Long.valueOf(schoolid));
			if(paperSchool.getId()==null){
				paperSchoolDao.save(paperSchool);
			}else{
				paperSchoolDao.update(paperSchool);
			}


		}
	}

	@Override
	public List<PaperSchoolDO> listPlan(Map<String, Object> map) {
		return paperSchoolDao.listPlan(map);
	}


	@Override
	public int countPlan(Map<String, Object> map) {
		return paperSchoolDao.countPlan(map);
	}

}
