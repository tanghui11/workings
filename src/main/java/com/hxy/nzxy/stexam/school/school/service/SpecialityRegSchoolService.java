package com.hxy.nzxy.stexam.school.school.service;

import com.hxy.nzxy.stexam.domain.SpecialityDO;
import com.hxy.nzxy.stexam.domain.SpecialityRegDO;

import java.util.List;
import java.util.Map;

/**
 * 专业开设备案
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 20:36:41
 */
public interface SpecialityRegSchoolService {
	
	SpecialityRegDO get(Long id);
	
	List<SpecialityRegDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SpecialityRegDO specialityRegSchool);
	
	int update(SpecialityRegDO specialityRegSchool);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    SpecialityRegDO getSpecialityRecordid(SpecialityRegDO specialityRegSchool);

    void batchAuditStatus(Long[] ids, String status);

	List<SpecialityRegDO> schoolSpeciality(Map<String, Object> map);

	int getSpecialityCount(List<SpecialityRegDO> specialityRegSchoolList);

	List<SpecialityDO> getSpeciality(List<SpecialityRegDO> specialityRegSchoolList);

	List<SpecialityDO> getSpeciality2(Map<String,Object> map);
}
