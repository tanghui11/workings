package com.hxy.nzxy.stexam.center.plan.service;

import com.hxy.nzxy.stexam.domain.CertificateReplaceItemDO;
import com.hxy.nzxy.stexam.domain.CommonCourseReplaceItemNewDO;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 证书顶替课程
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
public interface CertificateReplaceItemService {
	
	CertificateReplaceItemDO get(Long id);
	
	List<CertificateReplaceItemDO> list(Map<String, Object> map);

	List<CommonCourseReplaceItemNewDO> itemList(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(CertificateReplaceItemDO certificateReplaceItem);
	
	int update(CertificateReplaceItemDO certificateReplaceItem);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	String ZSIbatchImport(Long fatherCourseId, String fileName, MultipartFile file);
}
