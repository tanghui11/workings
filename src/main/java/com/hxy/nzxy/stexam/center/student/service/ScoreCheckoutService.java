package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.center.student.domain.FirstScoreCheckoutDO;
import com.hxy.nzxy.stexam.center.student.domain.ThirdScoreCheckoutDO;
import com.hxy.nzxy.stexam.common.utils.Query;

import java.util.List;

public interface ScoreCheckoutService {
    List<FirstScoreCheckoutDO> firstScoreList(Query query);

    int firstCount(Query query);

    int secondCount(Query query);

    int thirdCount(Query query);

    List<FirstScoreCheckoutDO> secondScorelist(Query query);

    List<ThirdScoreCheckoutDO> thirdScorelist(Query query);

    List<ThirdScoreCheckoutDO> fourthScorelist(Query query);

    int fourthCount(Query query);
}
