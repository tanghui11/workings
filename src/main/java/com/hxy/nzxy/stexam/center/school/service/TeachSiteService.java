package com.hxy.nzxy.stexam.center.school.service;

import com.hxy.nzxy.stexam.domain.TeachSiteDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 教学点管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:27
 */
public interface TeachSiteService {
	
	TeachSiteDO get(Long id);
	
	List<TeachSiteDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TeachSiteDO teachSite);
	
	int update(TeachSiteDO teachSite);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    int batchUpdateAudit(Long[] ids, String auditStatus);

	int updateAudit(Map<String,Object> params);

	//批量导入
	String batchImport(String fileName, MultipartFile file,String collegeId);
}
