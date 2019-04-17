package com.hxy.nzxy.stexam.region.student.service.impl;

import com.hxy.nzxy.stexam.domain.CertificateReplaceItemDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.SchoolScoreRegionDao;
import com.hxy.nzxy.stexam.domain.SchoolScoreDO;
import com.hxy.nzxy.stexam.region.student.service.SchoolScoreRegionService;



@Service
public class SchoolScoreRegionServiceImpl implements SchoolScoreRegionService {
	@Autowired
	private SchoolScoreRegionDao schoolScoreRegionDao;
	
	@Override
	public SchoolScoreDO get(Long id){
		return schoolScoreRegionDao.get(id);
	}
	
	@Override
	public List<SchoolScoreDO> list(Map<String, Object> map) throws ParseException{

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time1 = "";
			String time2 = "";
			if ("a".equals(map.get("year"))){
				time1 = map.get("time")+"-01-01 00:00:00";
				time2 = map.get("time")+"-06-30 23:59:59";
			}
			if("b".equals(map.get("year"))){
				time1 = map.get("time")+"-07-01 00:00:00";
				time2 = map.get("time")+"-12-31 23:59:59";
			}
			map.put("time1",time1);
			map.put("time2",time2);
		return schoolScoreRegionDao.list(map);
	}
	@Override
	public List<SchoolScoreDO> listT(Map<String, Object> map) throws ParseException{

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time1 = "";
			String time2 = "";
			if ("a".equals(map.get("year"))){
				time1 = map.get("time")+"-01-01 00:00:00";
				time2 = map.get("time")+"-06-30 23:59:59";
			}
			if("b".equals(map.get("year"))){
				time1 = map.get("time")+"-07-01 00:00:00";
				time2 = map.get("time")+"-12-31 23:59:59";
			}
			map.put("time1",time1);
			map.put("time2",time2);
		return schoolScoreRegionDao.listT(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return schoolScoreRegionDao.count(map);
	}
	
	@Override
	public int save(SchoolScoreDO schoolScoreRegion){
		return schoolScoreRegionDao.save(schoolScoreRegion);
	}
	
	@Override
	public int update(SchoolScoreDO schoolScoreRegion){
		return schoolScoreRegionDao.update(schoolScoreRegion);
	}
	
	@Override
	public int remove(Long id){
		return schoolScoreRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return schoolScoreRegionDao.batchRemove(ids);
	}

	@Override
	public int updateStatus(SchoolScoreDO schoolScoreRegionList){
		return schoolScoreRegionDao.updateStatus(schoolScoreRegionList);
	}
	
}
