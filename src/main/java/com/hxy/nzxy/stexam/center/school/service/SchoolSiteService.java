package com.hxy.nzxy.stexam.center.school.service;

import com.hxy.nzxy.stexam.domain.SchoolSiteDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 办学地区管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:27
 */
public interface SchoolSiteService {
	
	SchoolSiteDO get(Long id);
	
	List<SchoolSiteDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SchoolSiteDO schoolSite);
	
	int update(SchoolSiteDO schoolSite);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    void batchAuditStatus(Long[] ids, String status);

	List<SchoolSiteDO> listSchoolSite(Map<String,Object> params);

    //批量导入
    String batchImport(String fileName, MultipartFile file ,String schoolsid);
}
