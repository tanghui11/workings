package com.hxy.nzxy.stexam.school.school.dao;

import com.hxy.nzxy.stexam.domain.ScoreSchoolSetDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 校考课程录入设置
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 20:36:41
 */
@Mapper
public interface ScoreSchoolSetSchoolDao {

	ScoreSchoolSetDO get(Long id);
	
	List<ScoreSchoolSetDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreSchoolSetDO scoreSchoolSetSchool);
	
	int update(ScoreSchoolSetDO scoreSchoolSetSchool);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
