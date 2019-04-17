package com.hxy.nzxy.stexam.region.exam.dao;

import com.hxy.nzxy.stexam.domain.ExamTimeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考试时间
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-11 10:32:22
 */
@Mapper
public interface ExamTimeRegionDao {

	ExamTimeDO get(Long id);
	
	List<ExamTimeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExamTimeDO examTimeRegion);
	
	int update(ExamTimeDO examTimeRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
