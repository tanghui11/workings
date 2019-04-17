package com.hxy.nzxy.stexam.center.plan.service;

import com.hxy.nzxy.stexam.domain.SpecialityDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 专业管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
public interface SpecialityService {
	
	SpecialityDO get(String id);
	
	List<SpecialityDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SpecialityDO speciality);
	
	int update(SpecialityDO speciality);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	List<SpecialityDO> listSpeciality(List<String> list);

    String batchImport(String fileName, MultipartFile file);
}
