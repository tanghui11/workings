package com.hxy.nzxy.stexam.center.student.service.impl;

import com.hxy.nzxy.stexam.center.student.dao.ScoreCheckoutDao;
import com.hxy.nzxy.stexam.center.student.domain.FirstScoreCheckoutDO;
import com.hxy.nzxy.stexam.center.student.domain.ThirdScoreCheckoutDO;
import com.hxy.nzxy.stexam.center.student.service.ScoreCheckoutService;
import com.hxy.nzxy.stexam.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ScoreCheckoutServiceImpl implements ScoreCheckoutService {
	@Autowired
	private ScoreCheckoutDao scoreCheckoutDao;

	@Override
	public List<FirstScoreCheckoutDO> firstScoreList(Query query) {
		return scoreCheckoutDao.firstScorelist(query);
	}

	@Override
	public int firstCount(Query query) {
		return scoreCheckoutDao.firstCount(query);
	}

	@Override
	public List<FirstScoreCheckoutDO> secondScorelist(Query query) {
		return scoreCheckoutDao.secondScorelist(query);
	}

	@Override
	public int secondCount(Query query) {
		return scoreCheckoutDao.secondCount(query);
	}

	@Override
	public List<ThirdScoreCheckoutDO> thirdScorelist(Query query) {
		return scoreCheckoutDao.thirdScorelist(query);
	}

	@Override
	public int thirdCount(Query query) {
		return scoreCheckoutDao.thirdCount(query);
	}

	@Override
	public List<ThirdScoreCheckoutDO> fourthScorelist(Query query) {
		return scoreCheckoutDao.fourthScorelist(query);
	}

	@Override
	public int fourthCount(Query query) {
		return scoreCheckoutDao.fourthCount(query);
	}
}
