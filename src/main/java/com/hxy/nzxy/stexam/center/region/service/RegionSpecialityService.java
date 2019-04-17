package com.hxy.nzxy.stexam.center.region.service;

import com.hxy.nzxy.stexam.domain.RegionSpecialityDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 考区专业课程报考
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:18
 */
public interface RegionSpecialityService {
	
	RegionSpecialityDO get(Long id);
	
	List<RegionSpecialityDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RegionSpecialityDO regionSpeciality);
	
	int update(RegionSpecialityDO regionSpeciality);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<RegionSpecialityDO> seachSubjectIdlist(Map<String, Object> map);

	int batchSave(List<RegionSpecialityDO> list);

	int countSubject(Map<String, Object> map);

    //批量导入
    String batchImport(String fileName, MultipartFile file,String regionid);

	void saveBatch(List<RegionSpecialityDO> userKnowledgeBaseList);
}
