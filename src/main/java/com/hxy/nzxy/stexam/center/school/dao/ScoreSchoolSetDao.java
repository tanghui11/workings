package com.hxy.nzxy.stexam.center.school.dao;

import com.hxy.nzxy.stexam.domain.ScoreSchoolSetDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 校考课程录入设置
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:27
 */
@Mapper
public interface ScoreSchoolSetDao {

	ScoreSchoolSetDO get(Long id);
	
	List<ScoreSchoolSetDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreSchoolSetDO scoreSchoolSet);
	
	int update(ScoreSchoolSetDO scoreSchoolSet);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
