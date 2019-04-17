package com.hxy.nzxy.stexam.center.plan.dao;

import com.hxy.nzxy.stexam.domain.SpecialityDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 专业管理
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
@Mapper
public interface SpecialityDao {

	SpecialityDO get(String id);
	
	List<SpecialityDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SpecialityDO speciality);
	
	int update(SpecialityDO speciality);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

    List<SpecialityDO> listSpeciality(List<String> list);

    List<SpecialityDO> listCZ(List<SpecialityDO> userKnowledgeBaseList);

	void saveBatch(List<SpecialityDO> userKnowledgeBaseList);
}
