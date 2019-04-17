package com.hxy.nzxy.stexam.region.region.service;

import com.hxy.nzxy.stexam.domain.ExamSiteDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 考点维护
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:33
 */
public interface ExamSiteRegionService {
	
	ExamSiteDO get(Long id);
	
	List<ExamSiteDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExamSiteDO examSiteRegion);
	
	int update(ExamSiteDO examSiteRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	String batchImport(String fileName, MultipartFile file);
}
