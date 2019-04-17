package com.hxy.nzxy.stexam.center.plan.dao;

import com.hxy.nzxy.stexam.domain.CertificateReplaceDO;
import com.hxy.nzxy.stexam.domain.CertificateReplaceItemDO;
import com.hxy.nzxy.stexam.domain.CommonCourseReplaceItemDO;
import com.hxy.nzxy.stexam.domain.CommonCourseReplaceItemNewDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 证书顶替课程
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
@Mapper
public interface CertificateReplaceItemDao {

	CertificateReplaceItemDO get(Long id);
	
	List<CertificateReplaceItemDO> list(Map<String, Object> map);

	List<CommonCourseReplaceItemNewDO> itemList(Map<String, Object> map);

	int zsiSave(CertificateReplaceItemDO certificateReplace);

	int count(Map<String, Object> map);
	
	int save(CertificateReplaceItemDO certificateReplaceItem);
	
	int update(CertificateReplaceItemDO certificateReplaceItem);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<CertificateReplaceItemDO> listZSI(List<CertificateReplaceItemDO> userKnowledgeBaseList);
}
