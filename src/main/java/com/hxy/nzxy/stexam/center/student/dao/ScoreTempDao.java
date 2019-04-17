package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreDO;
import com.hxy.nzxy.stexam.domain.ScoreTempDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 登分表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
@Mapper
public interface ScoreTempDao {

	ScoreTempDO get(Long seatArrangeid);
	
	List<ScoreTempDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreTempDO scoreTemp);
	
	int update(ScoreTempDO scoreTemp);
	
	int remove(Long seat_arrangeid);
	
	int batchRemove(Long[] seatArrangeids);

	int saveList1(List<ScoreTempDO> list);
	int saveList2(List<ScoreTempDO> list);
	List<ScoreTempDO> listSame(List<ScoreTempDO> list);

	List<ScoreDO> listStuScore(List<ScoreTempDO> scoreTempDOS);

	void saveToScore(List<ScoreDO> scoreList);

	void batchRemoves(List<ScoreTempDO> scoreTempDOS);

    ScoreDO listStuScore2(ScoreTempDO scoreTempDO);
}
