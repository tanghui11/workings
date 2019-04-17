package com.hxy.nzxy.stexam.center.school.dao;

import com.hxy.nzxy.stexam.domain.SchoolSiteDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 办学地区管理
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:27
 */
@Mapper
public interface SchoolSiteDao {

	SchoolSiteDO get(Long id);
	
	List<SchoolSiteDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SchoolSiteDO schoolSite);
	
	int update(SchoolSiteDO schoolSite);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    void batchAuditStatus(Map map);

    List<SchoolSiteDO> listSchoolSite(Map<String,Object> params);

	List<SchoolSiteDO> listCZ(List<SchoolSiteDO> userKnowledgeBaseList);
}
