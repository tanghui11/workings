package com.hxy.nzxy.stexam.center.school.dao;

import com.hxy.nzxy.stexam.domain.SpecialityRegDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 专业开设备案
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:27
 */
@Mapper
public interface SpecialityRegDao {

	SpecialityRegDO get(Long id);
	
	List<SpecialityRegDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SpecialityRegDO specialityReg);
	
	int update(SpecialityRegDO specialityReg);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    int updateAudit(Map<String,Object> params);

	int batchUpdateAudit(Map params);

	List<SpecialityRegDO> listK(List<SpecialityRegDO> userKnowledgeBaseList);

}
