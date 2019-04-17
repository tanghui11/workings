package com.hxy.nzxy.stexam.center.school.service;

import com.hxy.nzxy.stexam.domain.SpecialityRegDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 专业开设备案
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:27
 */
public interface SpecialityRegService {
	
	SpecialityRegDO get(Long id);
	
	List<SpecialityRegDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SpecialityRegDO specialityReg);
	
	int update(SpecialityRegDO specialityReg);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	String batchImport(String fileName, MultipartFile file);

    int updateAudit(Map<String,Object> params);

    int batchUpdateAudit(Long[] ids, String auditStatus);

}
