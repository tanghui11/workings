package com.hxy.nzxy.stexam.school.school.dao;

import com.hxy.nzxy.stexam.domain.SchoolScoreRatioDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 平时成绩比例设置
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 20:36:41
 */
@Mapper
public interface SchoolScoreRatioSchoolDao {

	SchoolScoreRatioDO get(Long id);
	
	List<SchoolScoreRatioDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SchoolScoreRatioDO schoolScoreRatioSchool);
	
	int update(SchoolScoreRatioDO schoolScoreRatioSchool);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
