package com.hxy.nzxy.stexam.school.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreTempDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 登分表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
@Mapper
public interface ScoreTempStudentDao {

	ScoreTempDO get(Long seatArrangeid);
	
	List<ScoreTempDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ScoreTempDO scoreTempStudent);
	
	int update(ScoreTempDO scoreTempStudent);
	
	int remove(Long seat_arrangeid);
	
	int batchRemove(Long[] seatArrangeids);
}
