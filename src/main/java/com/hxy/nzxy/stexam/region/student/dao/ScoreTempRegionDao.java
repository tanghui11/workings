package com.hxy.nzxy.stexam.region.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreTempDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 登分表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
@Mapper
public interface ScoreTempRegionDao {

	ScoreTempDO get(Long seatArrangeid);
	
	List<ScoreTempDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ScoreTempDO scoreTempRegion);
	
	int update(ScoreTempDO scoreTempRegion);
	
	int remove(Long seat_arrangeid);
	
	int batchRemove(Long[] seatArrangeids);
}
