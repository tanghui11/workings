package com.hxy.nzxy.stexam.center.plan.service;

import com.hxy.nzxy.stexam.domain.CertificateReplaceDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 证书顶替
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
public interface CertificateReplaceService {
	
	CertificateReplaceDO get(Long id);
	
	List<CertificateReplaceDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CertificateReplaceDO certificateReplace);
	
	int update(CertificateReplaceDO certificateReplace);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	String ZSbatchImport(String fileName, MultipartFile file);

	String getFatherCourse(String courseReplaceId);
}
