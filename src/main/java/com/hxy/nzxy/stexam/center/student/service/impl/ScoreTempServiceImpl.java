package com.hxy.nzxy.stexam.center.student.service.impl;

import com.hxy.nzxy.stexam.domain.ScoreDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.ScoreTempDao;
import com.hxy.nzxy.stexam.domain.ScoreTempDO;
import com.hxy.nzxy.stexam.center.student.service.ScoreTempService;



@Service
public class ScoreTempServiceImpl implements ScoreTempService {
	@Autowired
	private ScoreTempDao scoreTempDao;
	
	@Override
	public ScoreTempDO get(Long seatArrangeid){
		return scoreTempDao.get(seatArrangeid);
	}
	
	@Override
	public List<ScoreTempDO> list(Map<String, Object> map){
		return scoreTempDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreTempDao.count(map);
	}
	
	@Override
	public int save(ScoreTempDO scoreTemp){
		return scoreTempDao.save(scoreTemp);
	}
	
	@Override
	public int update(ScoreTempDO scoreTemp){
		return scoreTempDao.update(scoreTemp);
	}
	
	@Override
	public int remove(Long seatArrangeid){
		return scoreTempDao.remove(seatArrangeid);
	}
	
	@Override
	public int batchRemove(Long[] seatArrangeids){
		return scoreTempDao.batchRemove(seatArrangeids);
	}

	@Override
	public int saveList1(List<ScoreTempDO> list) {
		return scoreTempDao.saveList1(list);
	}
	@Override
	public int saveList2(List<ScoreTempDO> list) {
		//将第二次成绩录入
		scoreTempDao.saveList2(list);
		//2次完成之后与第一次录入成绩比较取出2次成绩,缺考一样数据
		List<ScoreTempDO> scoreTempDOS = scoreTempDao.listSame(list);
		//查出正式表数据
		List<ScoreDO> scoreList =	scoreTempDao.listStuScore(scoreTempDOS);
		//将一样的数据录入正式表
		scoreTempDao.saveToScore(scoreList);
		scoreTempDao.batchRemoves(scoreTempDOS);
		return 1;
	}

	@Override
	public ScoreDO listStuScore2(ScoreTempDO scoreTempDO) {
		return scoreTempDao.listStuScore2(scoreTempDO);
	}
}
