package com.hxy.nzxy.stexam.center.plan.service;

import com.hxy.nzxy.stexam.domain.SpecialityRecordDO;
import com.hxy.nzxy.stexam.domain.SpecialityRegDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 专业开设管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:50
 */
public interface SpecialityRecordService {
	
	SpecialityRecordDO get(Long id);
	
	List<SpecialityRecordDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(SpecialityRecordDO specialityRecord);
	
	int update(SpecialityRecordDO specialityRecord);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    SpecialityRecordDO getSpecialityRecordid(SpecialityRegDO specialityRegSchool);

    //批量导入
    String batchImport(String fileName, MultipartFile file);

	void saveBatch(List<SpecialityRecordDO> userKnowledgeBaseList);
}
