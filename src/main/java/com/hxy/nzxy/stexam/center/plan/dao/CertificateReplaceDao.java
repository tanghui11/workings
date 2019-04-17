package com.hxy.nzxy.stexam.center.plan.dao;

import com.hxy.nzxy.stexam.domain.CertificateReplaceDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 证书顶替
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
@Mapper
public interface CertificateReplaceDao {

	CertificateReplaceDO get(Long id);
	
	List<CertificateReplaceDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CertificateReplaceDO certificateReplace);

	int zsSave(CertificateReplaceDO certificateReplace);

	int update(CertificateReplaceDO certificateReplace);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	String ZSbatchImport(String fileName, MultipartFile file);

	List<CertificateReplaceDO> listZS(List<CertificateReplaceDO> userKnowledgeBaseList);//!!!!!!!尚未实现

	String getCourseid(Long courseReplaceId);
}
