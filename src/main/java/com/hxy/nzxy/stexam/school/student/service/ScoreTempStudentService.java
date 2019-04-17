package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.domain.ScoreTempDO;

import java.util.List;
import java.util.Map;

/**
 * 登分表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
public interface ScoreTempStudentService {
	
	ScoreTempDO get(Long seatArrangeid);
	
	List<ScoreTempDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreTempDO scoreTempStudent);
	
	int update(ScoreTempDO scoreTempStudent);
	
	int remove(Long seatArrangeid);
	
	int batchRemove(Long[] seatArrangeids);
}
