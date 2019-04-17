package com.hxy.nzxy.stexam.system.dao;

import com.hxy.nzxy.stexam.system.domain.SubjectScienceAbilityDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 学科素养及目标
 * @author dragon
 * @email 330504176@qq.com
 * @date 2018-01-16 21:56:48
 */
@Mapper
public interface SubjectScienceAbilityDao {

	SubjectScienceAbilityDO get(Long id);
	
	List<SubjectScienceAbilityDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SubjectScienceAbilityDO subjectScienceAbility);
	
	int update(SubjectScienceAbilityDO subjectScienceAbility);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
