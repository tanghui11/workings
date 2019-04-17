package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.center.student.domain.FirstScoreCheckoutDO;
import com.hxy.nzxy.stexam.center.student.domain.ThirdScoreCheckoutDO;
import com.hxy.nzxy.stexam.common.utils.Query;

import java.util.List;

public interface ScoreCheckoutDao {

    List<FirstScoreCheckoutDO> firstScorelist(Query query);

    int firstCount(Query query);

    List<FirstScoreCheckoutDO> secondScorelist(Query query);

    int secondCount(Query query);

    List<ThirdScoreCheckoutDO> thirdScorelist(Query query);

    int thirdCount(Query query);

    List<ThirdScoreCheckoutDO> fourthScorelist(Query query);

    int fourthCount(Query query);


}
