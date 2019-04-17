package com.hxy.nzxy.stexam.center.school.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.SchoolSpecialityRegDO;

import java.util.List;
import java.util.Map;

/**
 * 专业招生备案
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:27
 */
public interface SchoolSpecialityRegService {
	
	SchoolSpecialityRegDO get(Long id);
	
	List<SchoolSpecialityRegDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SchoolSpecialityRegDO schoolSpecialityReg);
	
	int update(SchoolSpecialityRegDO schoolSpecialityReg);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	int batchUpdateAudit(Long[] ids, String auditStatus);

	int updateAudit(Map<String,Object> params);

    List<SchoolSpecialityRegDO> listSchoolSpeciality(Map<String, Object> map);

	int listSchoolcount(Map<String, Object> map);
}
