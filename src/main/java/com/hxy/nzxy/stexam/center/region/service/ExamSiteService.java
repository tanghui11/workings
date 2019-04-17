package com.hxy.nzxy.stexam.center.region.service;

import com.hxy.nzxy.stexam.domain.ExamSiteDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 考点维护
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:18
 */
public interface ExamSiteService {
	
	ExamSiteDO get(Long id);
	
	List<ExamSiteDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExamSiteDO examSite);
	
	int update(ExamSiteDO examSite);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	String batchImport(String fileName, MultipartFile file);
}
