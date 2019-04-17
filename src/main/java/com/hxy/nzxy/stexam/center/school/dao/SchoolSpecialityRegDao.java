package com.hxy.nzxy.stexam.center.school.dao;

import com.hxy.nzxy.stexam.domain.SchoolSpecialityRegDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 专业招生备案
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:27
 */
@Mapper
public interface SchoolSpecialityRegDao {

	SchoolSpecialityRegDO get(Long id);
	
	List<SchoolSpecialityRegDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SchoolSpecialityRegDO schoolSpecialityReg);
	
	int update(SchoolSpecialityRegDO schoolSpecialityReg);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    int batchUpdateAudit(Map params);

	int updateAudit(Map<String,Object> params);

    List<SchoolSpecialityRegDO> listSchoolSpeciality(Map<String,Object> map);

	int listSchoolcount(Map<String,Object> map);
}
