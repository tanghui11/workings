package com.hxy.nzxy.stexam.region.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreSchoolDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 校考成绩
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
@Mapper
public interface ScoreSchoolRegionDao {

	ScoreSchoolDO get(Long id);
	
	List<ScoreSchoolDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ScoreSchoolDO scoreSchoolRegion);
	
	int update(ScoreSchoolDO scoreSchoolRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
